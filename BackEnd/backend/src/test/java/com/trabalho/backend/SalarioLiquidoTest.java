package com.trabalho.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.trabalho.backend.model.Funcionario;
import com.trabalho.backend.model.OpcaoAdicional;
import com.trabalho.backend.service.OutrosCalculosService.CalcularSalarioLiquido;
import com.trabalho.backend.service.OutrosCalculosService.TotalSalarioBruto;
import com.trabalho.backend.service.calculoDescontosService.CalcularINSS;
import com.trabalho.backend.service.calculoDescontosService.CalcularIRRF;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeAlime;
import com.trabalho.backend.service.calculoDescontosService.CalcularValeTrans;

public class SalarioLiquidoTest {


    @Test

    void testarComSalarioBrutoApenas(){
        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularINSS calcINSS = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF calcIRRF = new CalcularIRRF(totalSalarioBruto, calcINSS);
        CalcularValeAlime calcValeAlime = new CalcularValeAlime();
        CalcularValeTrans calcValeTrans = new CalcularValeTrans(totalSalarioBruto);

        CalcularSalarioLiquido calcLiquido = new CalcularSalarioLiquido(
            totalSalarioBruto, calcINSS, calcIRRF, calcValeAlime, calcValeTrans
        );

        Funcionario f = new Funcionario();

        // Funcionário só com salário bruto
        f.setSalarioBase(2000.0);
        f.setReceberValeAlimentacao(false);
        f.setReceberValeTransporte(false);
        f.setDependentes(0);
        assertEquals(2000.0 - calcINSS.calcularDesconto(f) - calcIRRF.calcularDesconto(f),
                     calcLiquido.calcularLiquido(f));


    }


    @Test

    void TestarComAdicionaisEDescontos(){

        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularINSS calcINSS = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF calcIRRF = new CalcularIRRF(totalSalarioBruto, calcINSS);
        CalcularValeAlime calcValeAlime = new CalcularValeAlime();
        CalcularValeTrans calcValeTrans = new CalcularValeTrans(totalSalarioBruto);

        CalcularSalarioLiquido calcLiquido = new CalcularSalarioLiquido(
            totalSalarioBruto, calcINSS, calcIRRF, calcValeAlime, calcValeTrans
        );


        Funcionario f = new Funcionario();


        // Funcionário com adicionais/descontos
        f.setSalarioBase(2000.0);
        f.setPericulo(OpcaoAdicional.NAO); 
        f.setInsalu(OpcaoAdicional.NAO); 

        f.setReceberValeAlimentacao(true);
        f.setCustoDiarioAlimentacao(50.0);
        f.setDiasTrabalhadasMes(20);
        f.setReceberValeTransporte(true);
        f.setCustoValeTransporte(80.0);
        assertEquals(
            totalSalarioBruto.calcularSalarioTotalBruto(f)
            - calcINSS.calcularDesconto(f)
            - calcIRRF.calcularDesconto(f)
            - calcValeAlime.calcularDesconto(f)
            - calcValeTrans.calcularDesconto(f),
            calcLiquido.calcularLiquido(f)
        );
    }


    @Test
    void testarSalarioLiquidoTotal(){

        TotalSalarioBruto totalSalarioBruto = new TotalSalarioBruto();
        CalcularINSS calcINSS = new CalcularINSS(totalSalarioBruto);
        CalcularIRRF calcIRRF = new CalcularIRRF(totalSalarioBruto, calcINSS);
        CalcularValeAlime calcValeAlime = new CalcularValeAlime();
        CalcularValeTrans calcValeTrans = new CalcularValeTrans(totalSalarioBruto);

        CalcularSalarioLiquido calcLiquido = new CalcularSalarioLiquido(
            totalSalarioBruto, calcINSS, calcIRRF, calcValeAlime, calcValeTrans
        );


        Funcionario f = new Funcionario();


        // 3️⃣ Funcionário com todos os benefícios
        f.setSalarioBase(3000.0);
        f.setDependentes(2);
        f.setReceberValeAlimentacao(true);
        f.setCustoDiarioAlimentacao(50.0);
        f.setDiasTrabalhadasMes(22);
        f.setReceberValeTransporte(true);
        f.setCustoValeTransporte(100.0);

        assertEquals(1518.2570475, calcLiquido.calcularLiquido(f), 0.000001);

    }

}
