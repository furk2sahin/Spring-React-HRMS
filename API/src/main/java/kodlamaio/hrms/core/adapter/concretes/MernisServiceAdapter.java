package kodlamaio.hrms.core.adapter.concretes;

import kodlamaio.hrms.core.adapter.abstracts.UserCheckService;
import kodlamaio.hrms.externalServices.mernis.NVMKPSPublicSoap;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Locale;

@Service
public class MernisServiceAdapter implements UserCheckService {

    @Override
    public boolean validate(String nationalIdentity,
                            String name,
                            String surname,
                            Integer birthYear) {
        NVMKPSPublicSoap nvmkpsPublicSoap = new NVMKPSPublicSoap();
        Locale tr = new Locale("tr", "TR");
        try {
            return nvmkpsPublicSoap.TCKimlikNoDogrula(Long.parseLong(nationalIdentity),
                    name.toUpperCase(tr),
                    surname.toUpperCase(tr),
                    birthYear);
        } catch (NumberFormatException | RemoteException e) {
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
