package com.example.vxtuyen.firstopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by vxtuyen on 5/11/2015.
 */
public class Triangle {
    private FloatBuffer vertexBuffer;
    private float base = 0.1f;
    private float red, green, blue;
    private float vertices[] = {
            -0.5f, -0.5f, 0.0f,    //first vertex (x,y,z)
            0.5f, -0.5f, 0.0f,     //second vertex
            0.0f, 0.5f, 0.0f       //third vertex
    };

    public Triangle(float scale, float red, float green, float blue){
        vertices = new float[]{
                -base*scale, -base*scale, 0.0f,
                base*scale, -base*scale, 0.0f,
                0.0f, base*scale, 0.0f
        };
        this.red = red;
        this.green = green;
        this.blue = blue;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3*3*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.flip();
    }

    public void draw(GL10 gl10){
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glColor4f(red, green, blue, 0.5f);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl10.glDrawArrays(GL10.GL_TRIANGLES, 0, vertices.length/3);
        gl10.glDisableClientState((GL10.GL_VERTEX_ARRAY));
    }
}
