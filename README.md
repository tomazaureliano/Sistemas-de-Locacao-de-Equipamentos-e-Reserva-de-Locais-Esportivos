Sistema de Locação de Equipamentos e Reserva de Locais Esportivos
Descrição do Projeto
Este sistema tem como objetivo gerenciar clientes, locais esportivos e equipamentos, possibilitando o cadastro, reservas, locações, devoluções, pagamentos e geração de relatórios.
O projeto busca automatizar o processo de administração de centros esportivos, garantindo controle de disponibilidade, histórico de uso e integridade dos dados.

Integrantes do grupo com nome completo
*Aniely Carla Ferreira da Silva - anielycarlaf@gmail.com

*Aline de Oliveira Barbosa - alineoliveirab23@gmail.com

*Maria Eduarda de Souza - eduardasouzaa021@gmail.com

*Laura de Lima Cavalcante - estudando202f@gmail.com

*Tomaz Bruno Aureliano de Sant’anna Vieira - tomazbruno24@gmail.com

Requisitos Funcionais
1. Gerenciamento de Clientes
REQ01: Permitir cadastro de clientes com nome, documento, telefone e e-mail.
REQ02: Manter histórico de reservas de locais e locações de equipamentos realizadas por cada cliente.
2. Gerenciamento de Equipamentos
REQ03: Permitir cadastro de equipamentos com nome, tipo, quantidade, valor de locação e condição atual.
REQ04: Associar equipamentos a locais específicos.
REQ05: Controlar status dos equipamentos (disponível, emprestado, reservado, em manutenção).
3. Gerenciamento de Locais
REQ06: Permitir cadastro de locais esportivos com nome, tipo, capacidade, recursos disponíveis e valor de reserva.
REQ07: Controlar status dos locais (disponível, reservado, em manutenção).
4. Reservas de Locais e Equipamentos
REQ08: Permitir a reserva de locais para uma data e horário específicos, vinculando cliente e participantes.
REQ09: Permitir que uma reserva de local inclua também os equipamentos necessários, respeitando disponibilidade.
REQ10: Cancelar reservas apenas até um prazo limite antes do horário reservado.
REQ11: Impedir que um local ou equipamento seja reservado para dois clientes no mesmo horário.
5. Empréstimos e Devoluções de Equipamentos
REQ12: Permitir empréstimos de equipamentos vinculados a uma reserva de local ou a uma locação independente.
REQ13: Registrar devoluções de equipamentos, atualizando o estoque.
REQ14: Registrar atrasos na devolução, aplicando multa proporcional ao tempo excedido.
REQ15: Permitir registrar danos e encaminhar equipamentos para manutenção, alterando o status.
6. Pagamentos
REQ16: Permitir registro de pagamentos de reservas de locais e locações de equipamentos, incluindo valor total, data e método de pagamento.
REQ17: Controlar status do pagamento como pendente ou concluído.
REQ18: Permitir cobrança de multa em caso de cancelamento fora do prazo.
7. Relatórios e Estatísticas
REQ19: Gerar relatório de uso de locais por período, com taxa de ocupação.
REQ20: Gerar relatório de equipamentos mais utilizados em determinado período.
REQ21: Gerar relatório de clientes mais ativos em reservas e locações.
REQ22: Permitir exportação de relatórios em PDF e CSV, com filtros e agrupamentos.
8. Regras e Restrições
REQ23: Não permitir a reserva de local acima da capacidade máxima cadastrada.
REQ24: Não permitir reserva de equipamentos em quantidade superior ao estoque disponível.
REQ25: Bloquear reservas e empréstimos para clientes com pendências de devolução ou pagamento.
REQ26: Apenas funcionários autorizados podem registrar manutenção em equipamentos e locais.


