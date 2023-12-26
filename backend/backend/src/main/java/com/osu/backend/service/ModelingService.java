package com.osu.backend.service;

import com.osu.backend.dto.RequestDto;
import com.osu.backend.model.cargo.CargoType;
import com.osu.backend.model.request.Request;
import com.osu.backend.model.ship.Ship;
import com.osu.backend.model.ship.ShipType;
import com.osu.backend.repository.CraneRepository;
import com.osu.backend.repository.RequestRepository;
import com.osu.backend.repository.ShipRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class ModelingService {

    private final ShipService shipService;
    private final ShipRepository shipRepository;

    private final RequestRepository requestRepository;
    private final RequestService requestService;

    private CraneRepository craneRepository;

    private static final String[] SHIP_NAME= {"Yacht-", "Submarine-", "Sailboat-", "Hovercraft-", "Ferry-",
            "Vessel-", "Icebreaker-", "Catamaran-", "Speedboat-", "Dinghy-",
            "Fireboat-", "Tugboat-", "Canoe-", "Kayak-", "Ski-",
            "Pirate-", "Amphibious-", "Houseboat-", "Hoverboard-", "Jet-"};

    private static final String[] CARGO_NAME= {"Изумруды", "Платины", "Листья", "Аметисты", "Серебро",
            "Апельсины", "Цветы", "Титан", "Шафран", "Кофе",
            "Ваниль", "Лазурит", "Лимоны", "Индиго", "Золото",
            "Железо", "Масло", "Силикон", "Янтарь", "Гранаты"};

    public void startModeling(int step){
        requestRepository.deleteAllAndResetIds();
        requestRepository.resetAutoIncrement();

        generationShips();
        generationRequests();

    }

    public void generationRequests(){
        craneRepository.clearCraneStatus();
        craneRepository.clearCraneWorkload();

        List<Ship> ships = shipRepository.findAll();
        LocalDateTime time = LocalDateTime.now();

        for (Ship ship : ships){
            RequestDto requestDto = new RequestDto();
            requestDto.setArrival(time);
            requestDto.setId(ship.getId());
            requestService.createRequest(requestDto);
        }

        List<Request> requests = requestService.workPort();

        requestRepository.saveAll(requests);
    }

    public void generationShips(){
        shipRepository.deleteAllAndResetIds();
        shipRepository.resetAutoIncrement();

        int countShips = getRandomLen(1, SHIP_NAME.length);

        for (int i = 0; i < countShips; i++) {
            Long idType = getRandomId(1L, 3L);

            String shipName = SHIP_NAME[getRandomLen(0, SHIP_NAME.length - 1)];
            String shipNameFull = shipName + getRandomValue(100, 1000);

            String cargoName = CARGO_NAME[getRandomLen(0, CARGO_NAME.length - 1)];

            Integer shipWeight = getRandomValue(100, 1000);
            Integer shipValume = getRandomValue(100, 1000);

            Integer temp = 0;

            if(shipWeight < shipValume){
                temp = shipWeight;
                shipWeight = shipValume;
                shipValume = temp;
            }

            ShipType shipType = new ShipType(idType);
            CargoType cargoType = new CargoType(idType);

            String shipStatus = "0";
            Ship ship = new Ship(shipNameFull, shipWeight, shipType, cargoName, cargoType, shipValume, shipStatus );
            shipRepository.save(ship);
        }
    }

    public int getRandomLen(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public Long getRandomId(Long min, Long max) {
        Random random = new Random();
        return (long) (random.nextInt((int) (max - min + 1)) + min);
    }
    public Integer getRandomValue(Integer min, Integer max) {
        Random random = new Random();
        return random.nextInt((int) (max - min + 1)) + min;
    }
}
