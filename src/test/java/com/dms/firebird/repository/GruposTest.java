package com.dms.firebird.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.dms.firebird.AbstractTest;
import com.dms.firebird.model.Grupo;

public class GruposTest extends AbstractTest {

	@Autowired
	private Grupos repository;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected int countRowsInTable(String tableName) {
		return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
	}

	@Test
	public void deveObterOPrimeiraCategoria() throws Exception {
		Optional<Grupo> expected = Optional.of(new Grupo(1, "Capacitor"));
		Integer id = 1;

		Optional<Grupo> grupo = repository.findById(id);

		assertThat(grupo, equalTo(expected));
	}

	@Test
	public void deveListarGrupos() throws Exception {
		List<Grupo> grupos = repository.findAll();

		assertNotNull("Falha - esperando não nulo", grupos);
		// "debug"
		grupos.forEach(g -> System.out.printf("%d - %s\n", g.getId(), g.getDescricao()));
	}

	@Test
	public void deveContarQuantosGrupoTemNaTabela() throws Exception {
		final int expected = 60;

		final int count = countRowsInTable("tb_est_grupo");

		assertEquals(expected, count);
	}
}
