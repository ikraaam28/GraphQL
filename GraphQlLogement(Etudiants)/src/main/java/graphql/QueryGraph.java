package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entite.Logement;
import entite.RendezVous;
import repository.LogementRepository;
import repository.RendezVousRepository;

import java.util.List;

public class QueryGraph implements GraphQLRootResolver {
private RendezVousRepository rdvRep=new RendezVousRepository();
    private LogementRepository lgpRep=new LogementRepository();


    public QueryGraph(RendezVousRepository rdvRep, LogementRepository lgpRep) {
        this.rdvRep = rdvRep;
        this.lgpRep = lgpRep;
    }

    public List<RendezVous> getAllrdv(){
       return rdvRep.getListeRendezVous();
   }
    public List<Logement> getAlllogements(){
        return lgpRep.getAllLogements();
    }
    public List<RendezVous> getRendezVousByLogementRef(int reference) {
        return rdvRep.getListeRendezVousByLogementRef(reference);
    }
    public RendezVous getRendezVousById(int id) {
        return rdvRep.getListeRendezVous().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Logement> getAllLogements() {
        return lgpRep.getAllLogements();
    }
    public Logement getLogementByReference(int reference) {
        return lgpRep.getLogementsByReference(reference);
    }
    public List<Logement> getLogementsByType(Logement.Type type) {
        return lgpRep.getLogementsByType(type);
    }
}
