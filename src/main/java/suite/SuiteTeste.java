package suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.TesteCadastro;
import test.TesteCampoTreinamento;
import test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
	TesteCampoTreinamento.class
})
public class SuiteTeste {}