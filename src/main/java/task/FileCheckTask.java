package task;


import domain.BoardAttachVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mapper.BoardAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Component
public class FileCheckTask {

    @Setter(onMethod_= @Autowired)
    private BoardAttachMapper attachMapper;

    private String getFolderYesterDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        String str = sdf.format(cal.getTime());
        return str.replace("-", File.separator);
    }



    @Scheduled(cron="0 0 2 * * *")
    public void checkFiles()throws Exception{
        log.warn("File Check Task run........");
        log.warn("===========================");

        List<BoardAttachVO> fileList = attachMapper.getOldFiles();

        List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("/Users/jeongdaun/Desktop/Study/정다운/sadSpring/src/main/resources/upload/tmp",vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());

        fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("/Users/jeongdaun/Desktop/Study/정다운/sadSpring/src/main/resources/upload/tmp",vo.getUploadPath(),"s_"+vo.getUuid()+"_"+vo.getFileName())).forEach(p->fileListPaths.add(p));

        log.warn("===================begin");

        fileListPaths.forEach(p -> log.warn(p));

        File targetDir = Paths.get("/Users/jeongdaun/Desktop/Study/정다운/sadSpring/src/main/resources/upload/tmp",getFolderYesterDay()).toFile();

        File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath())==false);

        log.warn("===================end");
        for(File file: removeFiles) {
            log.warn(file.getAbsolutePath());
            file.delete();
        }
    }
}
