package org.exam;

public interface Constants {
     enum Orientation {
        E(0),
        S(1),
        W(2),
        N(3);
        private int neo;

        public Orientation toVisit(int val) {
            switch (val) {
                case 0:
                    return E;
                case 1:
                    return S;
                case 2:
                    return W;
                case 3:
                    return N;
                default:
                    return E;
            }
        }

        Orientation(int val) {
            this.neo = val;
        }
    }
}
