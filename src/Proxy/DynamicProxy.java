package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private Object target;
    private ActorProxy actorProxy;

    public static Object newInstance(Object target, ActorProxy actorProxy){
        Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(), interfaces, new DynamicProxy(target, actorProxy));
    }

    private DynamicProxy(Object target, ActorProxy actorProxy) {
        this.actorProxy = actorProxy;
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invocationResult = null;
        try
        {
            args[0] = actorProxy;
            invocationResult = method.invoke(this.target, args);
        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return invocationResult;
        }
    }
}

