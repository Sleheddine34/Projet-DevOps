package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Préparer les données simulées
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        // Appeler la méthode
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Vérifier le résultat
        assertNotNull(etudiants);
        assertEquals(2, etudiants.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEtudiant() {
        // Préparer les données simulées
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Appeler la méthode
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Vérifier le résultat
        assertNotNull(result);
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testAddEtudiant() {
        // Préparer les données simulées
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appeler la méthode
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Vérifier le résultat
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testModifyEtudiant() {
        // Préparer les données simulées
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appeler la méthode
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        // Vérifier le résultat
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRemoveEtudiant() {
        // Appeler la méthode
        etudiantService.removeEtudiant(1L);

        // Vérifier que la méthode deleteById a été appelée
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererEtudiantParCin() {
        // Préparer les données simulées
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findEtudiantByCinEtudiant(123456789L)).thenReturn(etudiant);

        // Appeler la méthode
        Etudiant result = etudiantService.recupererEtudiantParCin(123456789L);

        // Vérifier le résultat
        assertNotNull(result);
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(123456789L);
    }
}
