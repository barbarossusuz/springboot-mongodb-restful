package com.example.mongodb2.controller;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * Created by barbarossusuz on 17/07/2017.
 */

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private Environment env;

    private Mongo mongo = new Mongo("localhost", 27017);
    private DB db = this.mongo.getDB("imagedb");


    @GetMapping
    public void printImageList() {

        GridFS gfsPhoto = new GridFS(this.db, "photo");
        DBCursor cursor = gfsPhoto.getFileList();

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }


    @GetMapping(value = "/download/{imageName}")
    public ResponseEntity<?> downloadImage(@PathVariable("imageName") String imageName) {

        try {
            GridFS gfsPhoto = new GridFS(this.db, "photo");
            GridFSDBFile imageForOutput = gfsPhoto.findOne(imageName + ".jpg");
            imageForOutput.writeTo("/Users/barbarossusuz/Desktop/indirilen.jpg"); //output to  file

            System.out.println(imageForOutput);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/{imageName}")
    public ResponseEntity<?> deleteImage(@PathVariable("imageName") String imageName) {
        try {
            GridFS gfsPhoto = new GridFS(this.db, "photo");
            gfsPhoto.remove(gfsPhoto.findOne(imageName + ".jpg"));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile uploadFile) {
        try {

            // create a "photo" namespace
            GridFS gfsPhoto = new GridFS(this.db, "photo");


            String fileName = uploadFile.getOriginalFilename();

            // local image
            File imageFile = new File("/Users/barbarossusuz/Desktop/barbaros.jpg");


            InputStream in = uploadFile.getInputStream();

            // get image file from local drive or multipartfile
            GridFSInputFile gfsFile = gfsPhoto.createFile(in);

            // set a new filename for identify purpose
            gfsFile.setFilename(fileName);

            // save the image file into mongoDB
            gfsFile.save();
            System.out.println("Done");

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


//    String fileName = uploadFile.getOriginalFilename();
//    String directory = env.getProperty("upload.file.path");
//    String uploadFilePath = Paths.get("." + File.separator + directory, fileName).toString();
//
//    final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath)));
//            stream.write(uploadFile.getBytes());
//            stream.close();
}
