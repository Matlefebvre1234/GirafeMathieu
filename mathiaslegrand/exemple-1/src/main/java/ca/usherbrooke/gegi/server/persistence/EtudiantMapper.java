package ca.usherbrooke.gegi.server.persistence;

import ca.usherbrooke.gegi.server.business.Etudiant;

import ca.usherbrooke.gegi.server.business.Universite;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;


import java.util.List;

/**
 * Sert pas a grand chose
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Mapper
public interface EtudiantMapper {

    List<Etudiant> select(@Param("id") Integer id);
    void insertUniversite(@Param("universite") Universite universite);
    Etudiant insertEtudiant(@Param("etudiant") Etudiant etudiant);
    void insertAdminDB(@Param("cip") String cip);
}
