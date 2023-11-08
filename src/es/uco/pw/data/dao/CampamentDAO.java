package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

import es.uco.pw.business.campament.CampamentDTO;
import es.uco.pw.data.common.ConnectionDB;
import es.uco.pw.data.common.IDAO;



public class CampamentDAO implements IDAO<CampamentDTO>{

    public CampamentDAO(){}
    
    @Override
    public void insert(CampamentDTO campamentDTO) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql= "INSERT INTO campament (camp_id, initDate, finalDate, maxAssistants, level) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campamentDTO.getId());
            ps.setDate(2, Date.valueOf(campamentDTO.getInitDate()));
            ps.setDate(3, Date.valueOf(campamentDTO.getFinalDate()));
            ps.setInt(4, campamentDTO.getMaxAssistants());
            ps.setString(5,campamentDTO.getLevel().toString());

            ps.execute();

            for(int i=0; i<campamentDTO.getActivities().size(); i++){
                addActivity(campamentDTO.getId(), campamentDTO.getActivities().get(i).getname());
            }

            for (int i=0; i<campamentDTO.getMonitors().size(); i++){
                addMonitor(campamentDTO.getId(), campamentDTO.getMonitors().get(i).getID());
            }

        } catch (Exception e) { throw e; }
    }


    public void addActivity(int campId, String activityId) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql= "INSERT INTO activities_campaments (act_id, camp_id) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, activityId);
            ps.setInt(2, campId);

            ps.execute();

        } catch (Exception e) { throw e; }
    }

    public void addMonitor(int campId, int monitorId) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql= "INSERT INTO monitors_campaments (camp_id, monitor_id) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campId);
            ps.setInt(2, monitorId);

            ps.execute();

        } catch (Exception e) { throw e; }
    }

    public boolean existsEspecialAsistant(int campId) throws Exception{
        try{
            Connection conn = new ConnectionDB().getConnection();

            String sql= "SELECT i.ass_id FROM inscriptions i JOIN assistants a ON i.ass_id = a.ass_id  WHERE i.camp_id = ? and a.attention = true ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, campId);

            ResultSet  rs = ps.executeQuery();

            return rs.next();


        } catch (Exception e) { throw e; }
    }


    public ArrayList<CampamentDTO> getAll() throws Exception{
        return null;
    }
    public CampamentDTO getById() throws Exception{
        return null;
    }
    public void update(CampamentDTO dto) throws Exception{
        return;
    }



}