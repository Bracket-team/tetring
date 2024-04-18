package bracket.tetring.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 에러
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 접근 입니다."),
    PLAYER_ALREADY_PLAYING_STAGE(HttpStatus.BAD_REQUEST, "플레이어가 이미 스테이지를 플레이 중입니다."),
    PLAYER_ALREADY_IS_STORE(HttpStatus.BAD_REQUEST, "플레이어가 이미 상점에 있습니다."),
    NOT_GAME_OVER(HttpStatus.BAD_REQUEST, "아직 게임 오버가 되지 않았습니다."),
    GAME_OVER(HttpStatus.BAD_REQUEST, "이미 게임이 끝났습니다"),


    //403 에러
    CANT_BUY_BLOCK(HttpStatus.FORBIDDEN, "블록을 구매할 수 없습니다."),
    CANT_BUY_RELIC(HttpStatus.FORBIDDEN, "유물을 구매할 수 없습니다."),
    CANT_REROLL_RELIC(HttpStatus.FORBIDDEN, "유물을 리롤할 수 없습니다."),

    //404 에러
    PLAYER_BLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이어에게는 해당 블록이 없습니다."),
    PLAYER_RELIC_NOT_FOUND(HttpStatus.NOT_FOUND, "플레이어에게는 해당 유물이 없습니다."),
    STORE_BLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "상점에는 해당 블록이 없습니다."),
    STORE_RELIC_NOT_FOUND(HttpStatus.NOT_FOUND, "상점에는 해당 유물이 없습니다."),
    RELIC_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유물은 존재하지 않습니다."),
    GAME_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게임은 존재하지 않습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상점은 존재하지 않습니다.(게임을 먼저 만들어 주세요.)"),
    PLAYER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 플레이어는 존재하지 않습니다."),

    //409 에러
    PLAYER_BLOCK_ALREADY_SAVED(HttpStatus.CONFLICT, "해당 플레이어 블록 번호는 이미 저장되어 있습니다."),
    RELIC_ALREADY_SAVED(HttpStatus.CONFLICT, "해당 유물은 이미 저장되어 있습니다."),
    GAME_ALREADY_SAVED(HttpStatus.CONFLICT, "해당 게임은 이미 저장되어 있습니다."),
    STORE_ALREADY_SAVED(HttpStatus.CONFLICT, "해당 상점은 이미 저장되어 있습니다."),
    SCORE_ALREADY_SAVED(HttpStatus.CONFLICT, "해당 점수는 이미 저장되어 있습니다."),

    //500 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 발생 에러 입니다.");

    private final HttpStatus status;
    private final String message;
}
