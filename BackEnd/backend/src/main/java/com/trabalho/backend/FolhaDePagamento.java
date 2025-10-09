package com.trabalho.backend;
import com.trabalho.backend.model.Funcionario;
import java.time.LocalDate;

public class FolhaDePagamento {

    public static void main(String[] args) {
        System.out.println("Folha de Pagamento - Exemplo funcional");
        Funcionario f = new Funcionario();

	f.setNome("João");
	f.setCargo("Desenvolvedor");
	f.setSalarioBase(5000.0);
	f.setCargaHorariaDiaria(8.0);
	f.setHorasTrabalhadas(160);
	f.setDiasTrabalhadasSemana(5);
	f.setDiasTrabalhadasMes(22);
	f.setDataAdmissao(LocalDate.of(2022, 1, 10));
	f.setReceberValeTransporte(true);
	f.setReceberValeAlimentacao(true);
	f.setCustoValeTransporte(200.0);
	f.setCustoDiarioAlimentacao(25.0);
	f.setPericulosidade(false);
	f.setDependentes(2);

	System.out.println("Funcionário criado:");
	System.out.println("Nome: " + f.getNome());
	System.out.println("Cargo: " + f.getCargo());
	System.out.println("Salário Base: " + f.getSalarioBase());
	System.out.println("Carga Horária Diária: " + f.getCargaHorariaDiaria());
	System.out.println("Horas Trabalhadas: " + f.getHorasTrabalhadas());
	System.out.println("Dias Trabalhadas Semana: " + f.getDiasTrabalhadasSemana());
	System.out.println("Dias Trabalhadas Mês: " + f.getDiasTrabalhadasMes());
	System.out.println("Data de Admissão: " + f.getDataAdmissao());
	System.out.println("Receber VT: " + f.getReceberValeTransporte());
	System.out.println("Receber VA: " + f.getReceberValeAlimentacao());
	System.out.println("Custo VT: " + f.getCustoValeTransporte());
	System.out.println("Custo Diário Alimentação: " + f.getCustoDiarioAlimentacao());
	System.out.println("Periculosidade: " + f.getPericulosidade());
	System.out.println("Dependentes: " + f.getDependentes());



    }
}
