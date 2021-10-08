package pacoteDeTeste;

/**
 * Classe referente à validação
 * @author Guilherme Barbosa Ferreira
 * @version 1.0 (Out 2021)
 */

import static org.junit.jupiter.api.Assertions.*;
import viewCRUD.*;
import org.junit.jupiter.api.Test;

class TesteValidador {
	TelaPedido tp = new TelaPedido();
	TelaFunc tf = new TelaFunc();
	/**
	 * Testa se não retorna nulo nos métodos.
	 */
	@Test
	void testRetornoPedido() {
		assertNotNull(tp.retornaBebida());
		assertNotNull(tp.retornaOpcaoPastel());
		assertNotNull(tp.retornaSaboresPastel());
	}
	/**
	 * Testa se retorna verdadeiro para um CPF correto, e falso para CPF incorreto.
	 */
	@Test
	void testValidaCPF() {
		assertTrue(tf.validaCPF("05693818195"));
		assertFalse(tf.validaCPF("9999"));
		assertFalse(tf.validaCPF("55555555555"));
	}
}
