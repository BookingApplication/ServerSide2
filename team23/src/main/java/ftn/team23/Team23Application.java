package ftn.team23;

import ftn.team23.entities.Guest;
import ftn.team23.entities.Host;
import ftn.team23.repositories.IGuestRepository;
import ftn.team23.repositories.IHostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Team23Application {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(Team23Application.class, args);
    }

//    @Autowired
//    public  IGuestRepository g_repo;
//    @Autowired
//    public  IHostRepository h_repo;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Guest bav11 = new Guest();
//        bav11.setName("aaaa");
//        bav11.setSurname("jkhjhkj");
//        bav11.setEmail("bbbb");
//        bav11.setTelephoneNumber("789779");
//        bav11.setPassword("jkljklkjkl");
//        bav11.setLivingAddress("uuu ttt eee");
//        bav11.setEmailVerified(true);
//        g_repo.save(bav11);
//
//        Host ccv11 = new Host();
//        ccv11.setName("aaaa");
//        ccv11.setSurname("jkhjhkj");
//        ccv11.setEmail("bbbb");
//        ccv11.setTelephoneNumber("789779");
//        ccv11.setPassword("jkljklkjkl");
//        ccv11.setLivingAddress("uuu ttt eee");
//        ccv11.setEmailVerified(true);
//        h_repo.save(ccv11);
//
//        Guest bav12 = new Guest();
//        bav12.setName("tttttt");
//        bav12.setSurname("jkhjhkj");
//        bav12.setEmail("wwwwwww");
//        bav12.setTelephoneNumber("789779");
//        bav12.setPassword("uuyiuuuy");
//        bav12.setLivingAddress("uuu ooooo eee");
//        bav12.setEmailVerified(false);
//        g_repo.save(bav12);
//    }
}
