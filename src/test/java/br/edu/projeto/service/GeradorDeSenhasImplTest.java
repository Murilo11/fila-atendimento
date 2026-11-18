package br.edu.projeto.service;
import br.edu.projeto.model.TipoSenha;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class GeradorDeSenhasImplTest {
    @Test
    void returnsameInstancia(){
        GeradorDeSenhasImpl g1 = GeradorDeSenhasImpl.getInstance();
        GeradorDeSenhasImpl g2 = GeradorDeSenhasImpl.getInstance();
        assertNotNull(g1);
        assertSame(g1,g2);
    }
    @Test
    void gerarIndependentes(){
        GeradorDeSenhas gerador = GeradorDeSenhasImpl.getInstance();
        int comum1  = gerador.gerarSenha(TipoSenha.COMUM);
        int comum2  = gerador.gerarSenha(TipoSenha.COMUM);

        assertEquals(comum1 + 1 ,comum2);

        int pref1 = gerador.gerarSenha(TipoSenha.PREFERENCIAL);
        int pref2 = gerador.gerarSenha(TipoSenha.PREFERENCIAL);
        assertEquals(pref1 + 1 ,pref2);
    }

    @Test
    void gerarUnicasComThreads() throws InterruptedException {
        int numThreads = 10;
        int senhasPorThread = 100;
        int totalEsperado =  numThreads * senhasPorThread;

        Set <Integer> senhas = ConcurrentHashMap.newKeySet();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        GeradorDeSenhas gerador = GeradorDeSenhasImpl.getInstance();
        for (int i = 0; i < totalEsperado; i++) {
            executor.submit(() -> {
                int  numero =  gerador.gerarSenha(TipoSenha.COMUM);
                senhas.add(numero);
            });

        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(totalEsperado, senhas.size());
    }



}
