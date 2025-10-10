package com.trabalho.backend;
import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.GrauInsalubridade;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.OutrosCalculosService.CalcularFGTS;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioHora;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioLiquido;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularInsalubridade;
import com.trabalho.backend.service.calculoAdicionaisService.CalcularPericulosidade;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;

import java.time.LocalDate;

public class FolhaDePagamento {

    public static void main(String[] args) {
        System.out.println("Folha de Pagamento - Exemplo funcional");
        Funcionario f = new Funcionario();
		TotalSalarioBruto salarioBruto= new TotalSalarioBruto();
		CalcularInsalubridade insalubridade= new CalcularInsalubridade();
		CalcularPericulosidade periculosidade = new CalcularPericulosidade();
		CalcularINSS inss= new CalcularINSS(salarioBruto);
		CalcularIRRF irrf= new CalcularIRRF(salarioBruto, inss);
		CalcularValeAlime valeAlime= new CalcularValeAlime();
		CalcularValeTrans valeTrans= new CalcularValeTrans(salarioBruto);
		CalcularFGTS fgts= new CalcularFGTS(salarioBruto);
		CalcularSalarioHora salarioHora= new CalcularSalarioHora(salarioBruto);
		CalcularSalarioLiquido salarioLiquido= new CalcularSalarioLiquido(salarioBruto, inss, irrf, valeAlime, valeTrans);

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
		f.setInsalu(OpcaoAdicional.SIM);
		f.setPericulo(OpcaoAdicional.NAO);
		f.setInsalubridade(GrauInsalubridade.ALTO);
		f.setDependentes(2);

		// Visual retrô MS-DOS
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║               FOLHA DE PAGAMENTO - RETRÔ             ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║ %-20s: %-25s ║%n", "Nome", f.getNome());
        System.out.printf("║ %-20s: %-25s ║%n", "Cargo", f.getCargo());
        System.out.printf("║ %-20s: R$ %-23.2f ║%n", "Salário Base", f.getSalarioBase());
        System.out.printf("║ %-20s: %-25d ║%n", "Dependentes", f.getDependentes());
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Adicional de Periculosidade:", periculosidade.calcularAdicional(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Adicional de Insalubridade:", insalubridade.calcularAdicional(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Desconto INSS:", inss.calcularDesconto(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Desconto IRRF:", irrf.calcularDesconto(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Vale Alimentação:", valeAlime.calcularDesconto(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Vale Transporte:", valeTrans.calcularDesconto(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "FGTS:", fgts.calcularAdicional(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Salário Hora:", salarioHora.calcularAdicional(f));
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Salário Bruto:", salarioBruto.calcularSalarioTotalBruto(f));
        System.out.printf("║ %-25s R$ %-20.2f ║%n", "Salário Líquido:", salarioLiquido.calcularLiquido(f));
        System.out.println("╚══════════════════════════════════════════════════════╝");



    }
}
