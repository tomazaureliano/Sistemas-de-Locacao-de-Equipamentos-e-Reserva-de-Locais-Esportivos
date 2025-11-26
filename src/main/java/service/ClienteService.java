package service;
import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {
        // Dados Iniciais para teste
        clientes.add(new Cliente("Ana Silva", "111.222.333-44", "ana@teste.com", "9999-0000"));
        clientes.add(new Cliente("Bruno Souza", "555.666.777-88", "bruno@teste.com", "8888-1111"));
    }

    public List<Cliente> getClientes() { return clientes; }
    
    public void adicionar(Cliente c) { clientes.add(c); }
}