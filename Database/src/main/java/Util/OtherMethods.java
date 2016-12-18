package Util;

import models.Drug;

import java.util.LinkedList;
import java.util.List;

public class OtherMethods {
    public List<Integer> deleter(List<Integer> allId, List<Integer> deletingId, int own_id) {
        deletingId.add(own_id);
        for (int i=0; i<deletingId.size(); i++) {
            allId.remove(deletingId.get(i));
        }
        return allId;
    }
    public List<Integer> deleter(List<Integer> allId, List<Integer> deletingId) {
        for (int i=0; i<deletingId.size(); i++) {
            allId.remove(deletingId.get(i));
        }
        return allId;
    }
}
