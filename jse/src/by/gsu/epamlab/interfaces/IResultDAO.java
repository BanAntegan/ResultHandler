package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.results.Result;

public interface IResultDAO {
    Result nextResult();
    boolean hasResult();
    void closeReader();
}
