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
import java.util.Map;

@Configuration
public class Database {

    @Bean
    @Transactional
    CommandLineRunner runner(companyRepositories compRepo, eventRepositories eventRepo,
                             storeRepositories storeRepository, PasswordEncoder passwordEncoder){
        return args -> {
// --- 1. SAVE WINES FIRST (Prevents transient instance errors) ---
            Wine cabernet = new Wine("Cabernet Sauvignon", WINETYPE.RED);
            Wine pinotNoir = new Wine("Pinot Noir", WINETYPE.RED);
            Wine chianti = new Wine("Chianti", WINETYPE.RED);
            Wine rioja = new Wine("Rioja", WINETYPE.RED);
            Wine syrah = new Wine("Syrah", WINETYPE.RED);
            Wine sauvignonBlanc = new Wine("Sauvignon Blanc", WINETYPE.WHITE);
            Wine pinotGrigio = new Wine("Pinot Grigio", WINETYPE.WHITE);
            Wine champagne = new Wine("Champagne", WINETYPE.SPARKLING);
            Wine sauternes = new Wine("Sauternes", WINETYPE.DESSERT);
            Wine port = new Wine("Port", WINETYPE.FORTIFIED);

            storeRepository.saveAll(List.of(
                    cabernet, pinotNoir, chianti, rioja, syrah,
                    sauvignonBlanc, pinotGrigio, champagne, sauternes, port
            ));

// --- 2. INSTANTIATE CHEESES ---
            Cheese cheddar = new Cheese("Cheddar");
            Cheese brie = new Cheese("Brie");
            Cheese parmesan = new Cheese("Parmesan");
            Cheese manchego = new Cheese("Manchego");
            Cheese pecorino = new Cheese("Pecorino");
            Cheese goatCheese = new Cheese("Goat Cheese");
            Cheese mozzarella = new Cheese("Mozzarella");
            Cheese gruyere = new Cheese("Gruyere");
            Cheese stilton = new Cheese("Stilton");
            Cheese roquefort = new Cheese("Roquefort");

// --- 3. MAP WINE COMPATIBILITY WITH SCORES ---
            cheddar.addCompatibleWine(cabernet, 95);

            brie.addCompatibleWine(champagne, 92);
            brie.addCompatibleWine(pinotNoir, 90);

            parmesan.addCompatibleWine(chianti, 94);

            manchego.addCompatibleWine(rioja, 98);

            pecorino.addCompatibleWine(chianti, 91);
            pecorino.addCompatibleWine(syrah, 89);

            goatCheese.addCompatibleWine(sauvignonBlanc, 96);

            mozzarella.addCompatibleWine(pinotGrigio, 88);

            gruyere.addCompatibleWine(champagne, 95);

            stilton.addCompatibleWine(port, 98);

            roquefort.addCompatibleWine(sauternes, 97);
            roquefort.addCompatibleWine(port, 93);

// --- 4. SAVE CHEESES ---
            storeRepository.saveAll(List.of(
                    cheddar, brie, parmesan, manchego, pecorino,
                    goatCheese, mozzarella, gruyere, stilton, roquefort
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
            champagne.addCompatibleCheese(gruyere, 95);

            sauternes.addCompatibleCheese(roquefort, 97);

            port.addCompatibleCheese(stilton, 98);
            port.addCompatibleCheese(roquefort, 93);

            storeRepository.saveAll(List.of(
                    cabernet, pinotNoir, chianti, rioja, syrah,
                    sauvignonBlanc, pinotGrigio, champagne, sauternes, port
            ));

            storeRepository.saveAll(List.of(
                    cheddar, brie, parmesan, manchego, pecorino,
                    goatCheese, mozzarella, gruyere, stilton, roquefort
            ));

// --- COMPANY & EVENT ---
            Company company = new Company("Amazon");
            String encodedPassword = passwordEncoder.encode("Mitchell");
            company.setEncryptedPassword(encodedPassword);
            compRepo.save(company);

            Event birthday = new Event("Mitchell's Birthday", company);
            birthday.setCheeseList(List.of(cheddar, brie, parmesan, manchego, pecorino));
            birthday.setWineList(List.of(cabernet, pinotNoir, chianti, rioja, syrah));
            eventRepo.save(birthday);

            compRepo.save(company);

        };
    }
}
