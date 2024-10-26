package graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.LogementRepository;
import repository.RendezVousRepository;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQlEndPoint extends SimpleGraphQLServlet {
public GraphQlEndPoint(){
    super(buildSchema());
}

private static GraphQLSchema buildSchema(){
     RendezVousRepository rdvRep=new RendezVousRepository();
     LogementRepository lgpRep=new LogementRepository();


    return SchemaParser.newParser().file("schema.graphqls").
           resolvers(new QueryGraph(rdvRep,lgpRep),new MutationGraph(rdvRep,lgpRep)).
           build().makeExecutableSchema();

}
}
