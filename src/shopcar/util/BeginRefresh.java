/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author info1
 */
public class BeginRefresh
{
    List<RefreshListener> listeners = new ArrayList<>();
    
    public void addListener(RefreshListener toAdd)
    {
        listeners.add(toAdd);
    }
    
    public void notifyListeners(Object obj)
    {
        listeners.forEach(c -> c.objectHasBeenSaved(obj));
    }
}
