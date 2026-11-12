package model.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.Paciente;
import model.Profissional;
import model.Servico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaDecoratorTest {

    Servico servicoClinicaGeral;
    Servico servicoOrtodontica;
    Servico servicoAnestesia;
    Servico servicoRadiografia;
    Servico servicoLimpeza;

    @Test
    @DisplayName("Should calculate General Clinic Consultation base price and description Successfully")
    void calculateConsultaClinicaGeralBaseSuccessfully() {
        //Arrange
        this.createServicos();

        //Act
        Consulta consulta = new ConsultaClinicaGeral(this.servicoClinicaGeral);

        //Assert
        assertEquals("Consulta Clínica Geral", consulta.getDescricao());
        assertEquals(150.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should calculate Orthodontic Consultation base price and description Successfully")
    void calculateConsultaOrtodonticaBaseSuccessfully() {
        //Arrange
        this.createServicos();

        //Act
        Consulta consulta = new ConsultaOrtodontica(this.servicoOrtodontica);

        //Assert
        assertEquals("Consulta Ortodôntica", consulta.getDescricao());
        assertEquals(200.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should decorate General Clinic Consultation with Anesthesia Successfully")
    void decorateConsultaClinicaGeralWithAnestesiaSuccessfully() {
        //Arrange
        this.createServicos();

        //Act
        Consulta consulta = new Anestesia(new ConsultaClinicaGeral(this.servicoClinicaGeral), this.servicoAnestesia);

        //Assert
        assertEquals("Consulta Clínica Geral + Anestesia", consulta.getDescricao());
        assertEquals(230.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should decorate General Clinic Consultation with Radiography Successfully")
    void decorateConsultaClinicaGeralWithRadiografiaSuccessfully() {
        //Arrange
        this.createServicos();

        //Act
        Consulta consulta = new Radiografia(new ConsultaClinicaGeral(this.servicoClinicaGeral), this.servicoRadiografia);

        //Assert
        assertEquals("Consulta Clínica Geral + Radiografia", consulta.getDescricao());
        assertEquals(210.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should decorate Orthodontic Consultation with Cleaning Successfully")
    void decorateConsultaOrtodonticaWithLimpezaSuccessfully() {
        //Arrange
        this.createServicos();

        //Act
        Consulta consulta = new Limpeza(new ConsultaOrtodontica(this.servicoOrtodontica), this.servicoLimpeza);

        //Assert
        assertEquals("Consulta Ortodôntica + Limpeza", consulta.getDescricao());
        assertEquals(300.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should calculate price with two chained procedures (Anesthesia and Cleaning) Successfully")
    void calculatePriceWithTwoChainedProceduresSuccessfully() {
        //Arrange
        this.createServicos();

        //Act
        Consulta consulta = new Limpeza(
                new Anestesia(new ConsultaClinicaGeral(this.servicoClinicaGeral), this.servicoAnestesia),
                this.servicoLimpeza
        );

        //Assert
        assertEquals("Consulta Clínica Geral + Anestesia + Limpeza", consulta.getDescricao());
        assertEquals(330.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should calculate total price and description with all three procedures Successfully")
    void calculateTotalWithAllThreeProceduresSuccessfully() {
        //Arrange
        this.createServicos();
        ConsultaFactory factory = new ConsultaFactory();
        List<Servico> adicionais = Arrays.asList(this.servicoAnestesia, this.servicoRadiografia, this.servicoLimpeza);

        //Act
        Consulta consulta = factory.criar(this.servicoOrtodontica, adicionais);

        //Assert
        assertEquals("Consulta Ortodôntica + Anestesia + Radiografia + Limpeza", consulta.getDescricao());
        assertEquals(440.0, consulta.getPreco());
    }

    @Test
    @DisplayName("Should schedule consultation and update professional availability Successfully")
    void scheduleConsultaSuccessfully() {
        //Arrange
        this.createServicos();
        model.Consulta consultaAgendamento = this.createConsultaAgendamento(
                Arrays.asList(this.servicoClinicaGeral, this.servicoAnestesia)
        );

        //Act
        double valorTotal = consultaAgendamento.agendarConsulta();

        //Assert
        assertEquals(230.0, valorTotal);
        assertEquals("AGENDADA", consultaAgendamento.getStatus());
        assertFalse(consultaAgendamento.getProfissional().isStatusDisponibilidade());
    }

    @Test
    @DisplayName("Should schedule consultation with empty service list UnSuccessfully")
    void scheduleConsultaWithoutServicosUnSuccessfully() {
        //Arrange
        this.createServicos();
        List<Servico> servicosVazios = new ArrayList<>();
        model.Consulta consultaAgendamento = this.createConsultaAgendamento(servicosVazios);

        //Act
        double valorTotal = consultaAgendamento.agendarConsulta();

        //Assert
        assertTrue(consultaAgendamento.getServicos().isEmpty());
        assertEquals(0.0, valorTotal);
    }

    private void createServicos() {
        // contém os 5 serviços base e adicionais do padrão Decorator

        Servico.ServicoBuilder b1 = new Servico.ServicoBuilder();
        b1.comIdServico(1);
        b1.comNomeServico("Consulta Clínica Geral");
        b1.comValorServico(150.0);
        b1.comDuracaoServicoMinutos(30);
        this.servicoClinicaGeral = b1.constroi();

        Servico.ServicoBuilder b2 = new Servico.ServicoBuilder();
        b2.comIdServico(2);
        b2.comNomeServico("Consulta Ortodôntica");
        b2.comValorServico(200.0);
        b2.comDuracaoServicoMinutos(30);
        this.servicoOrtodontica = b2.constroi();

        Servico.ServicoBuilder b3 = new Servico.ServicoBuilder();
        b3.comIdServico(3);
        b3.comNomeServico("Anestesia");
        b3.comValorServico(80.0);
        b3.comDuracaoServicoMinutos(10);
        this.servicoAnestesia = b3.constroi();

        Servico.ServicoBuilder b4 = new Servico.ServicoBuilder();
        b4.comIdServico(4);
        b4.comNomeServico("Radiografia");
        b4.comValorServico(60.0);
        b4.comDuracaoServicoMinutos(15);
        this.servicoRadiografia = b4.constroi();

        Servico.ServicoBuilder b5 = new Servico.ServicoBuilder();
        b5.comIdServico(5);
        b5.comNomeServico("Limpeza");
        b5.comValorServico(100.0);
        b5.comDuracaoServicoMinutos(30);
        this.servicoLimpeza = b5.constroi();
    }

    private model.Consulta createConsultaAgendamento(List<Servico> servicos) {
        Paciente.PacienteBuilder pacBuilder = new Paciente.PacienteBuilder();
        pacBuilder.comIdPessoa(1);
        pacBuilder.comNome("Sabrina");
        Paciente paciente = pacBuilder.constroi();

        Profissional.ProfissionalBuilder profBuilder = new Profissional.ProfissionalBuilder();
        profBuilder.comIdPessoa(2);
        profBuilder.comNome("Gustavo");
        profBuilder.estaDisponivel(true);
        Profissional profissional = profBuilder.constroi();

        model.Consulta.ConsultaBuilder consultaBuilder = new model.Consulta.ConsultaBuilder();
        consultaBuilder.comIdConsulta(1);
        consultaBuilder.comPaciente(paciente);
        consultaBuilder.comProfissional(profissional);
        consultaBuilder.comServicos(servicos);
        consultaBuilder.comDataHora(LocalDateTime.of(2026, 11, 20, 14, 0));
        consultaBuilder.comStatus("PENDENTE");
        return consultaBuilder.constroi();
    }
}
