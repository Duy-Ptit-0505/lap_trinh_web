package com.htdweb.repository.custom.impl;

import com.htdweb.entity.BuildingEntity;
import com.htdweb.model.dto.SearchDTO;
import com.htdweb.repository.custom.BuildingRepositoryCustom;
import com.htdweb.utils.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public static void joinTable(SearchDTO searchDTO, StringBuilder sql){
        String category = searchDTO.getCategory();
        if(StringUtils.check(category)){
            sql.append(" INNER JOIN categories on b.category_id = categories.id ");
        }
    }
    public static void querySpecial(SearchDTO searchDTO, StringBuilder where){
        String category = searchDTO.getCategory();
        if(StringUtils.check(category)){
            where.append(" AND categories.code LIKE '%" + category + "%' ");
        }
    }
    public static void queryNomal(SearchDTO searchDTO, StringBuilder where){
        try{
            Field field[] = SearchDTO.class.getDeclaredFields();
            for(Field item : field){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("category")){
                    Object value =  item.get(searchDTO);
                    if(value != null && !value.equals("") ){
                        if(item.getType().getName().equals("java.lang.Integer")){
                            where.append(" AND b." + fieldName + " = " + value);

                        }else if (item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<BuildingEntity> getAllBuildingByCustom(SearchDTO searchDTO) {
        StringBuilder sql = new StringBuilder("SELECT b.*  FROM buildings b ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        joinTable(searchDTO, sql);
        queryNomal(searchDTO, where);
        querySpecial(searchDTO, where);
        where.append(" AND b.status = 1 ");
        where.append(" GROUP BY b.id ");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public List<BuildingEntity> getTop5BuildingByView() {
        String query = "SELECT b.* FROM buildings b WHERE b.status = 1 ORDER BY b.view DESC LIMIT 5";
        return entityManager.createNativeQuery(query, BuildingEntity.class).getResultList();
    }
}
