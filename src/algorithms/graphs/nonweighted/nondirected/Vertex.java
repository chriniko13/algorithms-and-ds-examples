package algorithms.graphs.nonweighted.nondirected;

import java.util.Objects;

public class Vertex {

    private final String label;
    private Boolean visited;

    public Vertex(String label, Boolean visited) {
        this.label = label;
        this.visited = visited;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.label);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{label = " + label + ", visited = " + visited + "}";
    }

}
