const xAuthToken = "4ff426076a04d781a3c43930a71e1b59";

const baseURL =
  "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";

let authKey;

const startAPI = (problem) => {
  return fetch(`${baseURL}/start`, {
    method: "POST",
    headers: {
      "X-Auth-Token": xAuthToken,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ problem: problem }),
  })
    .then((res) => res.json())
    .then((data) => data)
    .catch((e) => {
      console.log("[StartAPI] Error: ", e);
    });
};

const locationsAPI = () => {
  return fetch(`${baseURL}/locations`, {
    method: "GET",
    headers: {
      Authorization: authKey,
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => data)
    .catch((e) => {
      console.log("[LocationsAPI] Error: ", e);
    });
};

const truksAPI = () => {
  return fetch(`${baseURL}/trucks`, {
    method: "GET",
    headers: {
      Authorization: authKey,
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => data)
    .catch((e) => {
      console.log("[TrucksAPI] Error: ", e);
    });
};

const simulateAPI = (commands) => {
  return fetch(`${baseURL}/simulate`, {
    method: "PUT",
    headers: {
      Authorization: authKey,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ commands: commands }),
  })
    .then((res) => res.json())
    .then((data) => data)
    .catch((e) => {
      console.log("[SimulateAPI] Error: ", e);
    });
};

const scoreAPI = () => {
  return fetch(`${baseURL}/score`, {
    method: "GET",
    headers: {
      Authorization: authKey,
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => data)
    .catch((e) => {
      console.log("[ScoreAPI] Error: ", e);
    });
};

const findRectangle = (x1, y1, x2, y2) => {
  const minX = Math.min(x1, x2);
  const maxX = Math.max(x1, x2);
  const minY = Math.min(y1, y2);
  const maxY = Math.max(y1, y2);

  const p1 = [minX, minY];
  const p2 = [maxX, maxY];

  return { p1, p2 };
};

const test = async () => {
  const simulate = async () => {
    const location = await locationsAPI();

    const manyBikesArea = [];
    const zeroBikeArea = [];

    location.locations.forEach((element) => {
      if (element.located_bikes_count === 0) {
        zeroBikeArea.push(element.id);
      } else if (element.located_bikes_count > 1) {
        manyBikesArea.push(element.id);
      }
    });

    let commands = [];

    if (zeroBikeArea.length !== 0) {
      const trucks = await truksAPI();
      let truckWorking = new Array(5).fill(false);

      for (areaId of zeroBikeArea) {
        let dist = 100;
        let selectedTruck = undefined;

        const areaX = Math.floor(areaId / 5);
        const areaY = areaId % 5;

        for (truck of trucks.trucks) {
          if (truckWorking[truck.id]) continue;

          const truckX = Math.floor(truck.location_id / 5);
          const truckY = truck.location_id % 5;

          const currentDist =
            Math.abs(areaX - truckX) + Math.abs(areaY - truckY);

          if (dist > currentDist) {
            selectedTruck = truck;
            dist = currentDist;
          }
        }

        if (selectedTruck === undefined) break;

        truckWorking[selectedTruck.id] = true;
        const sTruckX = Math.floor(selectedTruck.location_id / 5);
        const sTruckY = selectedTruck.location_id % 5;

        const rectangle = findRectangle(areaX, areaY, sTruckX, sTruckY);
        let targetPoint = -1;

        for (let i = rectangle.p1[0]; i <= rectangle.p2[0]; i++) {
          if (targetPoint !== -1) break;
          for (let j = rectangle.p1[1]; j <= rectangle.p2[1]; j++) {
            const id = i * 5 + j;
            if (manyBikesArea.includes(id)) {
              targetPoint = id;
              const index = manyBikesArea.indexOf(id);
              manyBikesArea.splice(index, 1);
              break;
            }
          }
        }

        if (targetPoint !== -1) {
          let currentCommand = [];
          const tx = Math.floor(targetPoint / 5);
          const ty = targetPoint % 5;

          let sx = sTruckX;
          let sy = sTruckY;

          while (sx !== tx) {
            if (sx < tx) {
              sx++;
              currentCommand.push(2);
            } else {
              sx--;
              currentCommand.push(4);
            }
          }

          while (sy !== ty) {
            if (sy < ty) {
              sy++;
              currentCommand.push(1);
            } else {
              sy--;
              currentCommand.push(3);
            }
          }

          currentCommand.push(5);

          while (sx !== areaX) {
            if (sx < areaX) {
              sx++;
              currentCommand.push(2);
            } else {
              sx--;
              currentCommand.push(4);
            }
          }

          while (sy !== areaY) {
            if (sy < areaY) {
              sy++;
              currentCommand.push(1);
            } else {
              sy--;
              currentCommand.push(3);
            }
          }

          currentCommand.push(6);

          commands.push({
            truck_id: selectedTruck.id,
            command: currentCommand,
          });
        }
      } //end for.
    } // 트럭 움직이는 로직.

    const simulateResponse = await simulateAPI(commands);

    if (simulateResponse.time >= 720) {
      clearInterval(interval);
      const score = await scoreAPI();
      console.log(score);
    }
  };
  const interval = setInterval(simulate, 100);
};

const simulation1 = async () => {
  const init = await startAPI(1);
  authKey = init.auth_key;
  test();
};

const simulation2 = async () => {
  const init = await startAPI(2);
  authKey = init.auth_key;
  test();
};

simulation1();
simulation2();
