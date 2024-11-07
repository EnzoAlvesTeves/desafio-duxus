package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * @author carlosau
 */
@Service
public class ApiService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        Time timeDaData = todosOsTimes.stream().filter(time -> {
            return time.getData().equals(data);
        }).findFirst().orElse(null);
        return timeDaData;
    }
    /**
     * Vai retornar o integrante que tiver presente na maior quantidade de times
     * dentro do período
     */

    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<Integrante, Integer> contador = new HashMap<>();

        todosOsTimes.stream()
                .filter(time -> time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial))
                .forEach(time -> time.getComposicaoTime().forEach(composicao -> {
                    Integer quantidade = contador.get(composicao.getIntegrante());

                    if (quantidade == null) {
                        contador.put(composicao.getIntegrante(), 1);
                    } else {
                        contador.put(composicao.getIntegrante(), quantidade+1);
                    }
                }));

        return Collections.max(contador.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */

    public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<Time, Integer> contador = new HashMap<>();

        todosOsTimes.stream()
                .filter(time -> time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial))
                .forEach(time -> {
                    Integer quantidade = contador.get(time);
                    if (quantidade == null) {
                        contador.put(time, 1);
                    } else {
                        contador.put(time, quantidade+1);
                    }
                });

        Time timeMaisComum = Collections.max(contador.entrySet(), Map.Entry.comparingByValue()).getKey();

        return timeMaisComum.getComposicaoTime()
                .stream()
                .map(composicaoTime -> composicaoTime.getIntegrante().getNome())
                .collect(Collectors.toList());
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Integer> contador = new HashMap<>();

        todosOsTimes.stream()
                .filter(time -> time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial))
                .forEach(time -> time.getComposicaoTime().forEach(composicao -> {
                    Integer quantidade = contador.get(composicao.getIntegrante().getFuncao());

                    if (quantidade == null) {
                        contador.put(composicao.getIntegrante().getFuncao(), 1);
                    } else {
                        contador.put(composicao.getIntegrante().getFuncao(), quantidade+1);
                    }
                }));

        return Collections.max(contador.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        Map<String, Integer> contador = new HashMap<>();

        todosOsTimes.stream()
                .filter(time -> time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial))
                .forEach(time -> time.getComposicaoTime().forEach(composicao -> {
                    Integer quantidade = contador.get(composicao.getIntegrante().getFranquia());

                    if (quantidade == null) {
                        contador.put(composicao.getIntegrante().getFranquia(), 1);
                    } else {
                        contador.put(composicao.getIntegrante().getFranquia(), quantidade+1);
                    }
                }));

        return Collections.max(contador.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Long> contador = new HashMap<>();

        todosOsTimes.stream()
                .filter(time -> time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial))
                .forEach(time -> time.getComposicaoTime().forEach(composicao -> {
                    Long quantidade = contador.get(composicao.getIntegrante().getFranquia());

                    if (quantidade == null) {
                        contador.put(composicao.getIntegrante().getFranquia(), 1L);
                    } else {
                        contador.put(composicao.getIntegrante().getFranquia(), quantidade+1);
                    }
                }));


        return contador;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Long> contador = new HashMap<>();

        todosOsTimes.stream()
                .filter(time -> time.getData().isBefore(dataFinal) && time.getData().isAfter(dataInicial))
                .forEach(time -> time.getComposicaoTime().forEach(composicao -> {
                    Long quantidade = contador.get(composicao.getIntegrante().getFuncao());

                    if (quantidade == null) {
                        contador.put(composicao.getIntegrante().getFuncao(), 1L);
                    } else {
                        contador.put(composicao.getIntegrante().getFuncao(), quantidade+1);
                    }
                }));

        return contador;
    }
}
