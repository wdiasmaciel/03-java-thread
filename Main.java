import tarefa.Tarefa;

public class Main {
    public static void main(String[] args) {
        Tarefa tarefa = new Tarefa("Thread A");
        Thread thread = new Thread(tarefa);

        // 1. O usuário dispara a tarefa:
        thread.start();

        // 2. Cenário de Teste controlado pela Thread Principal (Main)
        try {
            // CENÁRIO A: altere para 2000 ms para ver o cancelamento no passo 2 ou 3.
            // CENÁRIO B: Altere para 7000 ms para ver o backup terminar com sucesso.
            long tempoToleranciaUsuario = 2000; 
            
            Thread.sleep(tempoToleranciaUsuario); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. O tempo de tolerância acabou. Se a thread ainda estiver viva, enviamos o sinal
        if (thread.isAlive()) {
            System.out.println("\n[Main] Erro: O tempo limite estourou! Cancelando a tarefa...");
            thread.interrupt(); // Ativa a flag booleana dentro da thread;
        }
    }
}
