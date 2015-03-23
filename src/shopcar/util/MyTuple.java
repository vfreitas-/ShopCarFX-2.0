/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import java.util.Objects;

/**
 *
 * @author info1
 * @param <First>
 * @param <Second>
 */
public class MyTuple<First, Second>
{
    private final First first;
    private final Second second;
    
    public MyTuple(First t, Second i)
    {
        this.first = t;
        this.second = i;
    }

    /**
     * @return the t
     */
    public First getFirst()
    {
        return first;
    }

    /**
     * @return the i
     */
    public Second getSecond()
    {
        return second;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.first);
        hash = 67 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final MyTuple<?, ?> other = (MyTuple<?, ?>) obj;
        if (!Objects.equals(this.first, other.first))
        {
            return false;
        }
        if (!Objects.equals(this.second, other.second))
        {
            return false;
        }
        return true;
    }
    
    
}
