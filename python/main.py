import sys

class Street():
    def __init__(self, enter, leave, name, duration):
        self.id = id
        self.enter = int(enter)
        self.leave = int(leave)
        self.name = name
        self.duration = int(duration)

    def __str__(self):
        return '{} {} {} {}'.format(self.enter, self.leave, self.name, self.duration )

class Cars():
    def __init__(self, id, street_num, street_names):
        self.id = id
        self.street_num = int(street_num)
        self.street_names = street_names

def parse_input(file):
    # streets = []
    streets = {}
    cars = []
    with open(file, 'r') as f:
        S = V = 0
        for i, line in enumerate(f):
            line = line[:-1]
            if i == 0: duration, intersection_num, street_num, car_num, bonus = line.split(' ')
            else:
                temp = line.split(' ')
                if S < int(street_num):
                    streets[temp[2]] = (int(temp[0]), int(temp[1]), int(temp[3]))
                    # streets.append(Street(*(line.split(' '))))
                    S += 1
                else:
                    temp = line.split(' ')
                    curr_set = set()
                    for i in range(int(temp[0])):
                        curr_set.add(temp[i + 1])
                    cars.append(Cars(V, int(temp[0]), curr_set))
                    V += 1

    return streets, cars, duration, intersection_num, street_num, car_num, bonus


if __name__ == '__main__':
    streets, cars, duration, intersection_num, street_num, car_num, bonus = parse_input(sys.argv[1])

    # entering the intersection from a road
    intersections_in = {}

    # entering from a intersection into the road
    intersections_out = {}
    for key in streets.keys():
        enter, leave, duration = streets[key]

        if enter not in intersections_out:
            intersections_out[enter] = [(duration, key)]
        else:
            intersections_out[enter].append((duration, key))
        if leave not in intersections_in:
            intersections_in[leave] = [(duration, key)]
        else:
            intersections_in[leave].append((duration, key))

        # if streets[i].enter not in intersections_out:
        #     intersections_out[streets[i].enter] = [(streets[i].duration, streets[i].name)]
        # else:
        #     intersections_out[streets[i].enter].append((streets[i].duration, streets[i].name))
        # if streets[i].leave not in intersections_in:
        #     intersections_in[streets[i].leave] = [(streets[i].duration, streets[i].name)]
        # else:
        #     intersections_in[streets[i].leave].append((streets[i].duration, streets[i].name))


    # for i in range(len(streets)):
    #     temp = streets[i].__str__()
    #     print(temp)

    # print(total_num)
    print(intersections_in)
    print(intersections_out)
