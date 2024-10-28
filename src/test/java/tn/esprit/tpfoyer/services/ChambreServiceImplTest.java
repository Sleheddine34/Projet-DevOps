package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddChambre() {
        Chambre chambre = new Chambre(0L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertNotNull(result);
        assertEquals(101L, result.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRetrieveChambre() {
        Long chambreId = 1L;
        Chambre chambre = new Chambre(chambreId, 202L, TypeChambre.DOUBLE, null, null);
        when(chambreRepository.findById(chambreId)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(chambreId);

        assertNotNull(result);
        assertEquals(202L, result.getNumeroChambre());
        verify(chambreRepository, times(1)).findById(chambreId);
    }

    @Test
    void testRetrieveChambreNotFound() {
        Long chambreId = 99L;
        when(chambreRepository.findById(chambreId)).thenReturn(Optional.empty());

        Chambre result = chambreService.retrieveChambre(chambreId);

        assertNull(result);
        verify(chambreRepository, times(1)).findById(chambreId);
    }

    @Test
    void testRetrieveAllChambres() {
        List<Chambre> chambres = Arrays.asList(
                new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null),
                new Chambre(2L, 102L, TypeChambre.DOUBLE, null, null)
        );
        when(chambreRepository.findAll()).thenReturn(chambres);

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveAllChambresEmpty() {
        when(chambreRepository.findAll()).thenReturn(Collections.emptyList());

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertTrue(result.isEmpty());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testModifyChambre() {
        Chambre chambre = new Chambre(1L, 103L, TypeChambre.TRIPLE, null, null);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.modifyChambre(chambre);

        assertEquals(103L, result.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRemoveChambre() {
        Long chambreId = 1L;
        doNothing().when(chambreRepository).deleteById(chambreId);

        chambreService.removeChambre(chambreId);

        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        TypeChambre type = TypeChambre.SIMPLE;
        List<Chambre> chambres = Arrays.asList(new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null));
        when(chambreRepository.findAllByTypeC(type)).thenReturn(chambres);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(type);

        assertEquals(1, result.size());
        assertEquals(TypeChambre.SIMPLE, result.get(0).getTypeC());
        verify(chambreRepository, times(1)).findAllByTypeC(type);
    }
}