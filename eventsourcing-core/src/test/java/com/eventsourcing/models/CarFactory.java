/**
 * Copyright (c) 2016, All Contributors (see CONTRIBUTORS file)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.eventsourcing.models;

import com.eventsourcing.EntityHandle;
import com.eventsourcing.ResolvedEntityHandle;
import com.googlecode.concurrenttrees.common.LazyIterator;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;

public class CarFactory {

    public static Set<EntityHandle<Car>>  createCollectionOfCars(int numCars) {
        Set<EntityHandle<Car>> cars = new LinkedHashSet<EntityHandle<Car>>(numCars);
        for (int carId = 0; carId < numCars; carId++) {
            cars.add(new ResolvedEntityHandle<>(createCar(carId)));
        }
        return cars;
    }

    public static Iterable<Car> createIterableOfCars(final int numCars) {
        final AtomicInteger count = new AtomicInteger();
        return new Iterable<Car>() {
            @Override
            public Iterator<Car> iterator() {
                return new LazyIterator<Car>() {
                    @Override
                    protected Car computeNext() {
                        int carId = count.getAndIncrement();
                        return carId < numCars ? createCar(carId) : endOfData();
                    }
                };
            }
        };
    }

    public static Car createCar(int carId) {
        switch (carId % 10) {
            case 0:
                return new Car(carId, "Ford", "Focus", Car.Color.RED, 5, 5000.00, noFeatures());
            case 1:
                return new Car(carId, "Ford", "Fusion", Car.Color.RED, 4, 3999.99, asList("hybrid"));
            case 2:
                return new Car(carId, "Ford", "Taurus", Car.Color.GREEN, 4, 6000.00, asList("grade a"));
            case 3:
                return new Car(carId, "Honda", "Civic", Car.Color.WHITE, 5, 4000.00, asList("grade b"));
            case 4:
                return new Car(carId, "Honda", "Accord", Car.Color.BLACK, 5, 3000.00, asList("grade c"));
            case 5:
                return new Car(carId, "Honda", "Insight", Car.Color.GREEN, 3, 5000.00, noFeatures());
            case 6:
                return new Car(carId, "Toyota", "Avensis", Car.Color.GREEN, 5, 5999.95, noFeatures());
            case 7:
                return new Car(carId, "Toyota", "Prius", Car.Color.BLUE, 3, 8500.00, asList("sunroof", "hybrid"));
            case 8:
                return new Car(carId, "Toyota", "Hilux", Car.Color.RED, 5, 7800.55, noFeatures());
            case 9:
                return new Car(carId, "BMW", "M6", Car.Color.BLUE, 2, 9000.23, asList("coupe"));
            default:
                throw new IllegalStateException();
        }
    }

    static List<String> noFeatures() {
        return Collections.<String>emptyList();
    }
}