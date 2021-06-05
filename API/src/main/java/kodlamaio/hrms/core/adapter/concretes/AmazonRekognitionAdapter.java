package kodlamaio.hrms.core.adapter.concretes;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.services.s3.AmazonS3;
import kodlamaio.hrms.core.adapter.abstracts.ImageCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Component
public class AmazonRekognitionAdapter implements ImageCheckerService {

    private final AmazonRekognition amazonRekognition;

    @Autowired
    public AmazonRekognitionAdapter(AmazonRekognition amazonRekognition) {
        this.amazonRekognition = amazonRekognition;
    }

    @Override
    public int detectPersonCount(MultipartFile image) {
        DetectFacesRequest request;
        DetectFacesResult result;
        List<FaceDetail> faceDetails;
        try{
            request = new DetectFacesRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
                    .withAttributes(Attribute.ALL);
            result = amazonRekognition.detectFaces(request);
            faceDetails = result.getFaceDetails();
        } catch (AmazonRekognitionException | IOException e) {
            return 0;
        }
        return faceDetails.size();
    }

    @Override
    public int detectModerationLabels(MultipartFile image) {
        DetectModerationLabelsRequest request;
        DetectModerationLabelsResult result;
        try{
            request = new DetectModerationLabelsRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
            result = amazonRekognition.detectModerationLabels(request);
        } catch (AmazonRekognitionException | IOException e){
            return 1;
        }
        return result.getModerationLabels().size();
    }
}
