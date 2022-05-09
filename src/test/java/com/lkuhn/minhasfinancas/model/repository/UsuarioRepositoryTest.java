package com.lkuhn.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lkuhn.minhasfinancas.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//scenery
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//action / execution
		boolean result = repository.existsByEmail("usuario@email.com");
	
		//verification
		Assertions.assertThat(result).isTrue();
		
	} 
		
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//scenery
		
		//action
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verification
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		//scenery
		Usuario usuario = criarUsuario();
		
		//action
		Usuario usuarioSalvo = repository.save(usuario);
		
		//verification
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();	
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() { 
		//scenery
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//verification
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat( result.isPresent() ).isTrue();
		
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() { 
		
		//verification
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat( result.isPresent() ).isTrue();
		
	}
	
	public static Usuario criarUsuario() {
		return Usuario
				.builder()
				.nome("usuario")
				.email("usuario@email.com")
				.senha("senha")
				.build();
	}
	
}
