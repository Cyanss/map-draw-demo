package com.bluethink.dao;

import com.bluethink.dto.GraphDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.Assert.*;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class GraphInfoDAOTest {
    @Autowired
    private GraphInfoDAO graphInfoDAO;

    @Test
    public void queryGraphInfoById() {
    }

    @Test
    public void queryGraphInfo() {
    }

    @Test
    public void insertGraphInfo() {
        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setUserId(1);
        graphDTO.setGraphName("polygonFF08E145");
        graphDTO.setGraphColor("FF08E145");
        graphDTO.setGraphType("polygon");
        graphDTO.setGraphData("62,79/62,79/101,129/101,129/81,164/81,164/39,141/39,141");
        graphDTO.setGraphContent("dec");
        Integer id = graphInfoDAO.insertGraphInfo(graphDTO);
        Assert.assertNotNull(id);
//        byte[] data;
//        FileImageInputStream input;
//        try {
//            File file = new File("D:\\Cyan\\Places\\ImagePlaces\\test.png");
//            if (file.exists()){
//                log.info("file {}", file.getName());
//            }
//            input = new FileImageInputStream(file);
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//            int numBytesRead = 0;
//            while ((numBytesRead = input.read(buf)) != -1) {
//                output.write(buf, 0, numBytesRead);
//            }
//            data = output.toByteArray();
//            output.close();
//            input.close();
//            log.info("data: {}", data);
//            graphDTO.setGraphData(data);
//            Integer id = graphInfoDAO.insertGraphInfo(graphDTO);
//            Assert.assertNotNull(id);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void deleteGraphInfo() {
    }

    @Test
    public void updateGraphInfo() {
        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setUserId(1);
        graphDTO.setGraphName("polygonFF08E145");
        graphDTO.setGraphColor("FF08E145");
        graphDTO.setGraphType("polygon");
        graphDTO.setGraphData("62,79/62,79/101,129/101,129/81,164/81,164/39,141/39,141");
        graphDTO.setGraphContent("dec");
        Integer id = graphInfoDAO.updateGraphInfo(28, graphDTO);
        Assert.assertNotNull(id);
    }

    @Test
    public void queryGraphInfoByUserId() {
    }

    @Test
    public void queryGraphInfoByName() {
    }
}