package br.com.b3teste.controller.job;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import br.com.b3teste.exception.UsuarioDuplicadoException;
import br.com.b3teste.model.User;
import br.com.b3teste.service.UserService;

@Component
public class UserJob {

	private static final Logger LOG = LoggerFactory.getLogger(UserJob.class);

	@Value("classpath:/app/files/*csv")
	private Resource[] resources;

	@Autowired
	private UserService userService;

	private List<String> processedFiles = new ArrayList<String>();

	@Scheduled(initialDelay = 0, fixedRate = 30000)
	public void importCSVFile() {

		LOG.info("Iniciado - JOB de importação do CSV");

		for (Resource resource : resources) {

			if (processedFiles.contains(resource.getFilename())) {
				LOG.info("Pulando a leitura do arquivo pois já foi processado " + resource.getFilename());
				continue;
			}

			LOG.info("Realizando a leitura do arquivo " + resource.getFilename());

			int processados = 0;
			try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
				String line;
				br.readLine();
				int count = 1;
				while ((line = br.readLine()) != null) {
					if (processLine(count, line)) {
						processados++;
					}
				}
			} catch (FileNotFoundException e) {
				LOG.error("Arquivo não encontrado", e);
			} catch (IOException e) {
				LOG.error("Erro na leitura do arquivo", e);
			} finally {
				LOG.info(
						"Finalizada a leitura do arquivo " + resource.getFilename() + " / Processados: " + processados);
				processedFiles.add(resource.getFilename());
			}
		}

		LOG.info("Finalizado - JOB de importação do CSV");
	}

	private boolean processLine(int count, String line) {
		String[] values = line.split(";");
		try {
			User user = new User(values[0], values[1], new SimpleDateFormat("dd/MM/yyyy").parse(values[2]));
			userService.save(user);
			return true;
		} catch (ParseException e) {
			LOG.error("Erro ao processar linha " + count + ": Data inválida!");
		} catch (UsuarioDuplicadoException e) {
			LOG.error("Erro ao processar linha " + count + ": Usuário já cadastrado!");
		} catch (TransactionSystemException e) {
			LOG.error("Erro ao processar linha " + count + ": " + getTransactionExceptionMessage(e));
		}
		return false;
	}

	private String getTransactionExceptionMessage(TransactionSystemException e) {
		if (e.getCause() instanceof RollbackException
				&& e.getCause().getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException constraintException = (ConstraintViolationException) e.getCause().getCause();
			return constraintException.getConstraintViolations().toString();
		} else {
			return e.getMessage();
		}
	}
}
