package tn.esprit.spring.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EtudiantServiceTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Given
        Etudiant etudiant1 = new Etudiant(1, "John", "Doe", 123456, null, null);
        Etudiant etudiant2 = new Etudiant(2, "Jane", "Doe", 654321, null, null);
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // When
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Then
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        // Given
        Etudiant etudiant = new Etudiant(1, "John", "Doe", 123456, null, null);
        when(etudiantRepository.save(any())).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Then
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(any());
    }

    public Etudiant updateEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    @Test
    void testRetrieveEtudiant() {
        // Given
        Etudiant etudiant = new Etudiant(1, "John", "Doe", 123456, null, null);
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // When
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Then
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testRemoveEtudiant() {
        // Given
        long idEtudiant = 1L;

        // When
        etudiantService.removeEtudiant(idEtudiant);

        // Then
        verify(etudiantRepository, times(1)).deleteById(idEtudiant);
    }
    public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
        List<Etudiant> findByReservationsAnneeUniversitaire(LocalDate anneeUniversitaire);
    }

    @Test
    void testFindByReservationsAnneeUniversitaire() {
        // Given
        Etudiant etudiant = new Etudiant(1, "John", "Doe", 123456, null, null);
        LocalDate currentDate = LocalDate.now();
        when(etudiantRepository.findByReservationsAnneeUniversitaire(currentDate)).thenReturn(Arrays.asList(etudiant));

        // When
        List<Etudiant> result = etudiantService.findByReservationsAnneeUniversitaire(currentDate);

        // Then
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNomEtudiant());
        verify(etudiantRepository, times(1)).findByReservationsAnneeUniversitaire(currentDate);
    }


}
