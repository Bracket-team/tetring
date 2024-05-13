package bracket.tetring;

import bracket.tetring.datainit.*;
import bracket.tetring.domain.game.repository.GameRepository;
import bracket.tetring.domain.game.repository.RelicRepository;
import bracket.tetring.domain.game.repository.ScoreRepository;
import bracket.tetring.domain.player.repository.PlayerBlockDeckRepository;
import bracket.tetring.domain.player.repository.PlayerRelicFoundRepository;
import bracket.tetring.domain.player.repository.PlayerRelicRepository;
import bracket.tetring.domain.player.repository.PlayerRepository;
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
//
//	@Bean
//	@Profile("local")
//	public TestDataInit testDataInit(PlayerRepository playerRepository, GameRepository gameRepository, ScoreRepository scoreRepository RelicRepository relicRepository) {
//		return new TestDataInit(relicRepository);
//	}

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
	public PlayerBlockDeckDataInit playerBlockDeckDataInit(GameRepository gameRepository, PlayerBlockDeckRepository playerBlockDeckRepository) {
		return new PlayerBlockDeckDataInit(gameRepository, playerBlockDeckRepository);
	}

	@Bean
	@Profile("local")
	public PlayerRelicDataInit playerRelicDataInit(GameRepository gameRepository, RelicRepository relicRepository, PlayerRelicRepository playerRelicRepository) {
		return new PlayerRelicDataInit(gameRepository, relicRepository, playerRelicRepository);
	}

	@Bean
	@Profile("local")
	public StoreDataInit storeDataInit(GameRepository gameRepository, StoreRepository storeRepository) {
		return new StoreDataInit(gameRepository, storeRepository);
	}
}
