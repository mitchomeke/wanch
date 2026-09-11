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
            if (storeRepository.count() > 0){
                return;
            }
// --- 1. SAVE WINES FIRST (Prevents transient instance errors) ---
            Wine cabernet = new Wine("Cabernet Sauvignon", WINETYPE.RED);
            Wine pinotNoir = new Wine("Pinot Noir", WINETYPE.RED);
            Wine chianti = new Wine("Chianti", WINETYPE.RED);
            Wine rioja = new Wine("Rioja", WINETYPE.RED);
            Wine syrah = new Wine("Syrah", WINETYPE.RED);
            Wine malbec = new Wine("Malbec", WINETYPE.RED);
            Wine nebbiolo = new Wine("Nebbiolo", WINETYPE.RED);
            Wine zinfandel = new Wine("Zinfandel", WINETYPE.RED);

            Wine sauvignonBlanc = new Wine("Sauvignon Blanc", WINETYPE.WHITE);
            Wine pinotGrigio = new Wine("Pinot Grigio", WINETYPE.WHITE);
            Wine chardonnay = new Wine("Chardonnay", WINETYPE.WHITE);
            Wine riesling = new Wine("Riesling", WINETYPE.WHITE);
            Wine viognier = new Wine("Viognier", WINETYPE.WHITE);

            Wine champagne = new Wine("Champagne", WINETYPE.SPARKLING);
            Wine prosecco = new Wine("Prosecco", WINETYPE.SPARKLING);

            Wine sauternes = new Wine("Sauternes", WINETYPE.DESSERT);
            Wine moscato = new Wine("Moscato d'Asti", WINETYPE.DESSERT);

            Wine port = new Wine("Port", WINETYPE.FORTIFIED);
            Wine sherry = new Wine("Sherry", WINETYPE.FORTIFIED);

            storeRepository.saveAll(List.of(
                    cabernet, pinotNoir, chianti, rioja, syrah, malbec, nebbiolo, zinfandel,
                    sauvignonBlanc, pinotGrigio, chardonnay, riesling, viognier,
                    champagne, prosecco, sauternes, moscato, port, sherry
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
            Cheese gouda = new Cheese("Aged Gouda");
            Cheese camembert = new Cheese("Camembert");
            Cheese gorgonzola = new Cheese("Gorgonzola");
            Cheese feta = new Cheese("Feta");
            Cheese fontina = new Cheese("Fontina");
            Cheese comte = new Cheese("Comté");

// --- 3. MAP WINE COMPATIBILITY ON CHEESES ---
            cheddar.addCompatibleWine(cabernet, 95);
            cheddar.addCompatibleWine(zinfandel, 91);

            brie.addCompatibleWine(champagne, 92);
            brie.addCompatibleWine(pinotNoir, 90);
            brie.addCompatibleWine(chardonnay, 88);

            parmesan.addCompatibleWine(chianti, 94);
            parmesan.addCompatibleWine(nebbiolo, 93);

            manchego.addCompatibleWine(rioja, 98);
            manchego.addCompatibleWine(sherry, 92);

            pecorino.addCompatibleWine(chianti, 91);
            pecorino.addCompatibleWine(syrah, 89);

            goatCheese.addCompatibleWine(sauvignonBlanc, 96);
            goatCheese.addCompatibleWine(viognier, 87);

            mozzarella.addCompatibleWine(pinotGrigio, 88);
            mozzarella.addCompatibleWine(prosecco, 85);

            gruyere.addCompatibleWine(champagne, 95);
            gruyere.addCompatibleWine(chardonnay, 91);

            stilton.addCompatibleWine(port, 98);
            stilton.addCompatibleWine(sauternes, 90);

            roquefort.addCompatibleWine(sauternes, 97);
            roquefort.addCompatibleWine(port, 93);

            gouda.addCompatibleWine(malbec, 94);
            gouda.addCompatibleWine(cabernet, 90);

            camembert.addCompatibleWine(pinotNoir, 93);
            camembert.addCompatibleWine(champagne, 89);

            gorgonzola.addCompatibleWine(moscato, 96);
            gorgonzola.addCompatibleWine(port, 91);

            feta.addCompatibleWine(sauvignonBlanc, 89);
            feta.addCompatibleWine(riesling, 92);

            fontina.addCompatibleWine(nebbiolo, 90);
            fontina.addCompatibleWine(pinotGrigio, 86);

            comte.addCompatibleWine(viognier, 94);
            comte.addCompatibleWine(syrah, 88);

// Save cheeses to store initial relationships
            storeRepository.saveAll(List.of(
                    cheddar, brie, parmesan, manchego, pecorino,
                    goatCheese, mozzarella, gruyere, stilton, roquefort,
                    gouda, camembert, gorgonzola, feta, fontina, comte
            ));

// --- 4. MAP CHEESE COMPATIBILITY ON WINES (Bidirectional) ---
            cabernet.addCompatibleCheese(cheddar, 95);
            cabernet.addCompatibleCheese(gouda, 90);

            pinotNoir.addCompatibleCheese(brie, 90);
            pinotNoir.addCompatibleCheese(camembert, 93);

            chianti.addCompatibleCheese(parmesan, 94);
            chianti.addCompatibleCheese(pecorino, 91);

            rioja.addCompatibleCheese(manchego, 98);

            syrah.addCompatibleCheese(pecorino, 89);
            syrah.addCompatibleCheese(comte, 88);

            malbec.addCompatibleCheese(gouda, 94);

            nebbiolo.addCompatibleCheese(parmesan, 93);
            nebbiolo.addCompatibleCheese(fontina, 90);

            zinfandel.addCompatibleCheese(cheddar, 91);

            sauvignonBlanc.addCompatibleCheese(goatCheese, 96);
            sauvignonBlanc.addCompatibleCheese(feta, 89);

            pinotGrigio.addCompatibleCheese(mozzarella, 88);
            pinotGrigio.addCompatibleCheese(fontina, 86);

            chardonnay.addCompatibleCheese(gruyere, 91);
            chardonnay.addCompatibleCheese(brie, 88);

            riesling.addCompatibleCheese(feta, 92);

            viognier.addCompatibleCheese(comte, 94);
            viognier.addCompatibleCheese(goatCheese, 87);

            champagne.addCompatibleCheese(brie, 92);
            champagne.addCompatibleCheese(gruyere, 95);
            champagne.addCompatibleCheese(camembert, 89);

            prosecco.addCompatibleCheese(mozzarella, 85);

            sauternes.addCompatibleCheese(roquefort, 97);
            sauternes.addCompatibleCheese(stilton, 90);

            moscato.addCompatibleCheese(gorgonzola, 96);

            port.addCompatibleCheese(stilton, 98);
            port.addCompatibleCheese(roquefort, 93);
            port.addCompatibleCheese(gorgonzola, 91);

            sherry.addCompatibleCheese(manchego, 92);

// --- 5. PERSIST FINAL MUTUAL STATE ---
            storeRepository.saveAll(List.of(
                    cabernet, pinotNoir, chianti, rioja, syrah, malbec, nebbiolo, zinfandel,
                    sauvignonBlanc, pinotGrigio, chardonnay, riesling, viognier,
                    champagne, prosecco, sauternes, moscato, port, sherry
            ));

            storeRepository.saveAll(List.of(
                    cheddar, brie, parmesan, manchego, pecorino,
                    goatCheese, mozzarella, gruyere, stilton, roquefort,
                    gouda, camembert, gorgonzola, feta, fontina, comte
            ));
        };
    }
}
