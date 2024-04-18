package bracket.tetring;

import bracket.tetring.datainit.*;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.player.repository.PlayerBlockRepository;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
import bracket.tetring.domain.store.repository.StoreBlockRepository;
import bracket.tetring.domain.store.repository.StoreRelicRepository;
import bracket.tetring.domain.store.repository.StoreRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TetringApplication {
	//jdbc:h2:tcp://localhost/~/tetring
	//jdbc:h2:~/tetring

	public static void main(String[] args) {
		SpringApplication.run(TetringApplication.class, args);
	}

	@Bean
	@Profile("local")
	public PlayerDataInit playerDataInit(PlayerRepository playerRepository) {
		return new PlayerDataInit(playerRepository);
	}

	@Bean
	@Profile("local")
	public GameDataInit gameDataInit(PlayerRepository playerRepository, GameRepository gameRepository) {
		return new GameDataInit(playerRepository, gameRepository);
	}

	@Bean
	@Profile("local")
	public ScoreDataInit scoreDataInit(PlayerRepository playerRepository, GameRepository gameRepository, ScoreRepository scoreRepository) {
		return new ScoreDataInit(playerRepository, gameRepository, scoreRepository);
	}

	@Bean
	@Profile("local")
	public RelicDataInit relicDataInit(RelicRepository relicRepository) {
		return new RelicDataInit(relicRepository);
	}

	@Bean
	@Profile("local")
	public PlayerRelicFoundDataInit playerRelicFoundDataInit(PlayerRepository playerRepository, RelicRepository relicRepository, PlayerRelicFoundRepository playerRelicFoundRepository) {
		return new PlayerRelicFoundDataInit(playerRepository, relicRepository, playerRelicFoundRepository);
	}

	@Bean
	@Profile("local")
	public PlayerBlockDataInit playerBlockDeckDataInit(PlayerRepository playerRepository, GameRepository gameRepository, PlayerBlockRepository playerBlockRepository) {
		return new PlayerBlockDataInit(playerRepository, gameRepository, playerBlockRepository);
	}

	@Bean
	@Profile("local")
	public PlayerRelicDataInit playerRelicDataInit(PlayerRepository playerRepository ,GameRepository gameRepository, RelicRepository relicRepository, PlayerRelicRepository playerRelicRepository) {
		return new PlayerRelicDataInit(playerRepository, gameRepository, relicRepository, playerRelicRepository);
	}

	@Bean
	@Profile("local")
	public StoreDataInit storeDataInit(PlayerRepository playerRepository, GameRepository gameRepository, StoreRepository storeRepository) {
		return new StoreDataInit(playerRepository, gameRepository, storeRepository);
	}

	@Bean
	@Profile("local")
	public StoreBlockDataInit storeBlockDataInit(PlayerRepository playerRepository, GameRepository gameRepository, StoreRepository storeRepository, StoreBlockRepository storeBlockRepository) {
		return new StoreBlockDataInit(playerRepository, gameRepository, storeRepository, storeBlockRepository);
	}

	@Bean
	@Profile("local")
	public StoreRelicDataInit storeRelicDataInit(PlayerRepository playerRepository, GameRepository gameRepository, StoreRepository storeRepository, RelicRepository relicRepository, StoreRelicRepository storeRelicRepository) {
		return new StoreRelicDataInit(playerRepository, gameRepository, storeRepository, relicRepository, storeRelicRepository);
	}
}
