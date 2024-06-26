//플레이어 왼쪽 혹은 오른쪽 누를 시 작동하는 함수
function playerMove(offset) {
  player.pos.x += offset;
  if (collide(area, player)) {
    player.pos.x -= offset;
  }
}

//플레이어 아래 방향키 누를 시 작동하는 함수
function playerDrop() {
  player.pos.y++;
  if (collide(area, player)) {
    player.pos.y--;
    blockDrop();
  }
}

//하드 드롭 기능
function playerHardDrop() {
  while (!collide(area, player)) {
    player.pos.y++;
  }
  // 마지막으로 이동했을 때 충돌이 발생했으므로 한 칸 위로 이동
  player.pos.y--;
  blockDrop();
}

function blockDrop() {
  mergeMatrix(area, player);
  blockPlacedCount++;
  if (blockPlacedCount % 4 === 0) {
    addGreyRow();
  }
  sweep();
  updateScore();
  loadNewBlock();
}

//dir은 방향(direction) 양수면 시계방향, 음수면 반시계방향
//블록을 회전하는 코드
function rotate(matrix, dir) {
  for (let y = 0; y < matrix.length; ++y) {
    for (let x = 0; x < y; ++x) {
      [matrix[x][y], matrix[y][x]] = [matrix[y][x], matrix[x][y]];
    }
  }
  if (dir > 0) {
    matrix.forEach((row) => row.reverse());
  } else {
    matrix.reverse();
  }
}

function playerRotate(dir) {
  const pos = player.pos.x;
  let offset = 1;
  rotate(player.matrix, dir);
  //회전 한 뒤에 충돌이 날 경우 x값을 좌우로 바꿔가며 위치 조절
  while (collide(area, player)) {
    player.pos.x += offset;
    // 1 -> -2 -> 3 -> -4 -> 5
    offset = -(offset + (offset > 0 ? 1 : -1));
    if (offset > player.matrix[0].length) {
      rotate(player.matrix, -dir);
      player.pos.x = pos;
      return;
    }
  }
}

//플레이어 hold 기능
function playerHold() {
  if (holdUsed) return; // 이미 hold를 사용한 경우 더 이상 진행하지 않음

  if (hold) {
    // hold와 player.matrix 교환
    [hold, player.matrix] = [player.matrix, hold];
  } else {
    // 처음 hold하는 경우, player.matrix hold에 저장
    hold = player.matrix;
    loadNewBlock();
  }
  player.pos.y = 0;
  player.pos.x =
    ((area[0].length / 2) | 0) - ((player.matrix[0].length / 2) | 0);
  holdUsed = true; // Hold 기능을 사용했음을 표시
}

function addGreyRow() {
  const width = 12; // 가로 칸 수
  const emptyPosition = Math.floor(Math.random() * width); // 무작위로 비어있을 칸 선택
  const newRow = new Array(width).fill(8); // 새로운 회색 줄 생성
  newRow[emptyPosition] = 0; // 선택된 위치는 비어있게 설정

  area.push(newRow); // 새로운 회색 줄을 가장 밑에 추가

  // 추가적으로, area의 크기가 게임 보드의 높이를 초과하지 않도록 조정
  if (area.length > 20) {
    // 가장 위의 줄에 0 초과인 값이 있는지 확인
    const topRow = area[0];
    const hasBlock = topRow.some((value) => value > 0);

    if (hasBlock) {
      gameOver();
    }
    area.shift(); // 가장 위의 줄을 제거하여 크기 조정
  }
}
