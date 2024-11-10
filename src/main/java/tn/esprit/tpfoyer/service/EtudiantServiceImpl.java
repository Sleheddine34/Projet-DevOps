package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

  public Optional<Etudiant> retrieveEtudiant(Long etudiantId) {
    return etudiantRepository.findById(etudiantId);
}

    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant modifyEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }

    public Etudiant recupererEtudiantParCin(long cin) {
        return etudiantRepository.findEtudiantByCinEtudiant(cin);
    }

    public List<Etudiant> findByReservationsAnneeUniversitaire(LocalDate anneeUniversitaire) {
        return etudiantRepository.findByReservationsAnneeUniversitaire(anneeUniversitaire);
    }
}
