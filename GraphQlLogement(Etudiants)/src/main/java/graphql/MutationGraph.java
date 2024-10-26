package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entite.Logement;
import entite.RendezVous;
import repository.LogementRepository;
import repository.RendezVousRepository;

public class MutationGraph implements GraphQLRootResolver {
    private RendezVousRepository rdvRep = new RendezVousRepository();
    private LogementRepository lgpRep = new LogementRepository();

    public MutationGraph(RendezVousRepository rdvRep, LogementRepository lgpRep) {
        this.rdvRep = rdvRep;
        this.lgpRep = lgpRep;
    }

    public RendezVous createRendezVous(int id, String date, String heure, int refLog,
                                       String num) {
        Logement l = lgpRep.getLogementsByReference(refLog);
        RendezVous r = new RendezVous(id, date, heure, l, num);

        // Check if the rendezvous was successfully added
        if (rdvRep.addRendezVous(r)) {
            return r;  // Return the created rendezvous
        } else {
            // Handle the case where it failed to add (optional: throw an exception)
            return null;  // or throw new RuntimeException("Failed to create RendezVous");
        }
    }
    public Boolean deleteRendezVousById(int id) {
        return rdvRep.deleteRendezVous(id);
    }
    public RendezVous updateRendezVous(int id, String date, String heure, String numTel) {
        return rdvRep.getListeRendezVous().stream()
                .filter(r -> r.getId() == id)
                .peek(rdv -> {
                    rdv.setDate(date);
                    rdv.setHeure(heure);
                    rdv.setNumTel(numTel);
                })
                .findFirst()
                .orElse(null); // Retourne null si le rendez-vous n'existe pas
    }

    public Logement createLogement(int reference, String adresse, String delegation, String gouvernorat, Logement.Type type, String description, float prix) {
        Logement logement = new Logement(reference, adresse, delegation, gouvernorat, type, description, prix);
        lgpRep.saveLogement(logement); // Ajouter le logement dans le repository
        return logement; // Retourner le logement créé
    }


}
