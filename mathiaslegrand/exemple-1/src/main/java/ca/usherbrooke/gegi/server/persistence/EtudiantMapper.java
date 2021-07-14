package ca.usherbrooke.gegi.server.persistence;

import ca.usherbrooke.gegi.server.business.Etudiant;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;


import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Mapper
public interface EtudiantMapper {

    List<Etudiant> select(@Param("id") Integer id);
    Etudiant insertEtudiant(@Param("etudiant") Etudiant etudiant);
    void insertAdminDB(@Param("cip") String cip);
}
