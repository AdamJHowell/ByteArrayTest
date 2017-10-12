import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class Main
{

	public static void main( String[] args )
	{
		int testInt = 256;
		byte[] intInByteArray = intToByteArray( testInt );
		int intFromByteArray = byteArrayToInt( intInByteArray );

		System.out.println( "\nBit-shifting test..." );
		System.out.println( "  Put in: " + testInt );         // prints '123'
		for( byte myByte : intInByteArray )
		{
			System.out.println( "\tBit-shifting: " + myByte );
		}
		System.out.println( "  Got out: " + intFromByteArray );        // prints '2063597568

		System.out.println( "\nByteBuffer test..." );
		byte[] intInByteBuffer = intToByteBuffer( testInt );
		int intFromByteBuffer = byteBufferToInt( intInByteBuffer );

		System.out.println( "  Put in " + testInt );
		for( byte myByte : intInByteBuffer )
		{
			System.out.println( "\tByteBuffer: " + myByte );
		}
		System.out.println( "  Got out " + intFromByteBuffer );
	}


	private static int byteArrayToInt( byte[] b )
	{
		// Shift left then bitwise AND against a mask of 1s.
		return b[3] & 0xFF |
			( b[2] & 0xFF ) << 8 |
			( b[1] & 0xFF ) << 16 |
			( b[0] & 0xFF ) << 24;
	}


	private static byte[] intToByteArray( int a )
	{
		// Shift right then bitwise AND against a mask of 1s.
		return new byte[]{
			( byte ) ( ( a >> 24 ) & 0xFF ),
			( byte ) ( ( a >> 16 ) & 0xFF ),
			( byte ) ( ( a >> 8 ) & 0xFF ),
			( byte ) ( a & 0xFF )
		};
	}


	private static int byteBufferToInt( byte[] b )
	{
		final ByteBuffer bb = ByteBuffer.wrap( b );
		// This line can be affixed to the previous line.  I put it here to make endianness more obvious.
		bb.order( ByteOrder.BIG_ENDIAN );
		return bb.getInt();
	}


	private static byte[] intToByteBuffer( int i )
	{
		final ByteBuffer bb = ByteBuffer.allocate( Integer.SIZE / Byte.SIZE );
		// This line can be affixed to the previous line.  I put it here to make endianness more obvious.
		bb.order( ByteOrder.BIG_ENDIAN );
		bb.putInt( i );
		return bb.array();
	}
}
