package com.example.wanch;
import com.example.wanch.resources.*;
import com.example.wanch.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Database {

    @Bean
    @Transactional
    CommandLineRunner runner(companyRepositories compRepo, eventRepositories eventRepo,
                             storeRepositories storeRepository, PasswordEncoder passwordEncoder){
        return args -> {
            // --- WINES ---
            Wine cabernet = new Wine("Cabernet Sauvignon", WINETYPE.RED);
            Wine pinotNoir = new Wine("Pinot Noir", WINETYPE.RED);
            Wine chianti = new Wine("Chianti Classico", WINETYPE.RED);
            Wine rioja = new Wine("Rioja (Tempranillo)", WINETYPE.RED);
            Wine syrah = new Wine("Syrah / Shiraz", WINETYPE.RED);
            Wine sauvignonBlanc = new Wine("Sauvignon Blanc", WINETYPE.WHITE);
            Wine pinotGrigio = new Wine("Pinot Grigio", WINETYPE.WHITE);
            Wine champagne = new Wine("Champagne", WINETYPE.SPARKLING);
            Wine sauternes = new Wine("Sauternes", WINETYPE.DESSERT);
            Wine port = new Wine("Port", WINETYPE.FORTIFIED);

            storeRepository.saveAll(List.of(
                    cabernet, pinotNoir, chianti, rioja, syrah,
                    sauvignonBlanc, pinotGrigio, champagne, sauternes, port
            ));

// --- CHEESES ---
            Cheese cheddar = new Cheese("Aged Cheddar", new ArrayList<>(List.of(cabernet)));
            Cheese brie = new Cheese("Brie", new ArrayList<>(List.of(champagne, pinotNoir)));
            Cheese parmesan = new Cheese("Parmesan", new ArrayList<>(List.of(chianti)));
            Cheese manchego = new Cheese("Manchego", new ArrayList<>(List.of(rioja)));
            Cheese pecorino = new Cheese("Pecorino", new ArrayList<>(List.of(chianti, syrah)));
            Cheese goatCheese = new Cheese("Goat Cheese", new ArrayList<>(List.of(sauvignonBlanc)));
            Cheese mozzarella = new Cheese("Mozzarella", new ArrayList<>(List.of(pinotGrigio)));
            Cheese brillatSavarin = new Cheese("Brillat-Savarin", new ArrayList<>(List.of(champagne)));
            Cheese stilton = new Cheese("Stilton", new ArrayList<>(List.of(port)));
            Cheese roquefort = new Cheese("Roquefort", new ArrayList<>(List.of(sauternes, port)));

            storeRepository.saveAll(List.of(
                    cheddar, brie, parmesan, manchego, pecorino,
                    goatCheese, mozzarella, brillatSavarin, stilton, roquefort
            ));

            cabernet.addCompatibleCheese(cheddar, 95);

            pinotNoir.addCompatibleCheese(brie, 90);

            chianti.addCompatibleCheese(parmesan, 94);
            chianti.addCompatibleCheese(pecorino, 91);

            rioja.addCompatibleCheese(manchego, 98);

            syrah.addCompatibleCheese(pecorino, 89);

            sauvignonBlanc.addCompatibleCheese(goatCheese, 96);

            pinotGrigio.addCompatibleCheese(mozzarella, 88);

            champagne.addCompatibleCheese(brie, 92);
            champagne.addCompatibleCheese(brillatSavarin, 95);

            sauternes.addCompatibleCheese(roquefort, 97); // Iconic French pairing

            port.addCompatibleCheese(stilton, 98);
            port.addCompatibleCheese(roquefort, 93);


            storeRepository.saveAll(List.of(
                    cabernet, pinotNoir, chianti, rioja, syrah,
                    sauvignonBlanc, pinotGrigio, champagne, sauternes, port
            ));

            storeRepository.saveAll(List.of(
                    cheddar, brie, parmesan, manchego, pecorino,
                    goatCheese, mozzarella, brillatSavarin, stilton, roquefort
            ));


            Company company = new Company("Amazon");
            String encodedPassword = passwordEncoder.encode("Mitchell");
            company.setEncryptedPassword(encodedPassword);
            compRepo.save(company);

            Event birthday = new Event("Mitchell's Birthday",company);
            birthday.setCheeseList(List.of(cheddar, brie, parmesan, manchego, pecorino));
            birthday.setWineList(List.of(cabernet, pinotNoir, chianti, rioja, syrah));
            eventRepo.save(birthday);

            compRepo.save(company);

        };
    }
}
