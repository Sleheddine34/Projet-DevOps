package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BlocServiceImplTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllBlocs() {
        // Préparer les données simulées
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();
        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc1, bloc2));

        // Appeler la méthode
        List<Bloc> blocs = blocService.retrieveAllBlocs();

        // Vérifier le résultat
        assertNotNull(blocs);
        assertEquals(2, blocs.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveBlocsSelonCapacite() {
        // Préparer les données simulées
        Bloc bloc1 = new Bloc();
        bloc1.setCapaciteBloc(100);
        Bloc bloc2 = new Bloc();
        bloc2.setCapaciteBloc(200);
        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc1, bloc2));

        // Appeler la méthode
        List<Bloc> result = blocService.retrieveBlocsSelonCapacite(150);

        // Vérifier le résultat
        assertEquals(1, result.size());
        assertEquals(200, result.get(0).getCapaciteBloc());
    }

    @Test
    void testRetrieveBloc() {
        // Préparer les données simulées
        Bloc bloc = new Bloc();
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        // Appeler la méthode
        Bloc result = blocService.retrieveBloc(1L);

        // Vérifier le résultat
        assertNotNull(result);
        verify(blocRepository, times(1)).findById(1L);
    }

    @Test
    void testAddBloc() {
        // Préparer les données simulées
        Bloc bloc = new Bloc();
        when(blocRepository.save(bloc)).thenReturn(bloc);

        // Appeler la méthode
        Bloc result = blocService.addBloc(bloc);

        // Vérifier le résultat
        assertNotNull(result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testModifyBloc() {
        // Préparer les données simulées
        Bloc bloc = new Bloc();
        when(blocRepository.save(bloc)).thenReturn(bloc);

        // Appeler la méthode
        Bloc result = blocService.modifyBloc(bloc);

        // Vérifier le résultat
        assertNotNull(result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testRemoveBloc() {
        // Appeler la méthode
        blocService.removeBloc(1L);

        // Vérifier que la méthode deleteById a été appelée
        verify(blocRepository, times(1)).deleteById(1L);
    }

    @Test
    void testTrouverBlocsSansFoyer() {
        // Préparer les données simulées
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();
        when(blocRepository.findAllByFoyerIsNull()).thenReturn(Arrays.asList(bloc1, bloc2));

        // Appeler la méthode
        List<Bloc> result = blocService.trouverBlocsSansFoyer();

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    @Test
    void testTrouverBlocsParNomEtCap() {
        // Préparer les données simulées
        Bloc bloc = new Bloc();
        when(blocRepository.findAllByNomBlocAndCapaciteBloc("Bloc A", 100)).thenReturn(Arrays.asList(bloc));

        // Appeler la méthode
        List<Bloc> result = blocService.trouverBlocsParNomEtCap("Bloc A", 100);

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc("Bloc A", 100);
    }
}
