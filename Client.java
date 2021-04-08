import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	public static void main(String args[]) throws Exception
	{
		// Request connection to server
		Socket s = new Socket("127.0.0.1", 1422);

		// Create text output and input streams
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());

		Scanner t = new Scanner(System.in);

		int n, i, c3;
		String c1, c2, msgin, msgout;

		// 01. Receive connection message
		msgin = dis.readUTF();
		System.out.println(msgin);

		while(true)
		{
			System.out.println("\n\nChoose one option:\n\n01.\tEnter '1' to perform new operation, \n02.\tEnter '2' to show the result of previous operations, or \n03.\tEnter '0' to exit.\n\nEnter your choice:");
			c1 = t.nextLine();
			// 02. Send the first choice
			dos.writeUTF(c1);
			dos.flush();

			if(c1.equals("1"))
			{
				System.out.println("\n\nTo select an operation:\n\n01.\tEnter '1' to calculate the value of Pi,\n02.\tEnter '2' to add two numbers,\n03.\tEnter '3' to sort an array, or\n04.\tEnter '4' to multiply three matrices.\n\nEnter your choice:");
				c2 = t.nextLine();

				// 03. Send the second choice
				dos.writeUTF(c2);
				dos.flush();

				if(c2.equals("2"))
				{
					// add(i, j)

					// 04. Send two numbers
					System.out.println("\nEnter the value of two numbers:");
					msgout=t.nextLine();
					dos.writeUTF(msgout);
					dos.flush();
					msgout=t.nextLine();
					dos.writeUTF(msgout);
					dos.flush();
				}

				if(c2.equals("3"))
				{
					// sort(arrayA)

					// 05. Send the number of elements in array
					System.out.println("\nEnter the number of elements in array:");
					msgout = t.nextLine();
					dos.writeUTF(msgout);
					dos.flush();

					n = Integer.parseInt(msgout);

					System.out.println("\nEnter the elements in array:");
					for(i=0;i<n;i++)
					{
						// 06. Send each element
						msgout = t.nextLine();
						dos.writeUTF(msgout);
						dos.flush();
					}
				}

				if(c2.equals("4"))
				{
					// Matrix multiplication

					// Accept matrices from the user
					System.out.println("\nEnter elements row-by-row for Matrix A:");
					for(i=0;i<9;i++)
					{
						n = t.nextInt();

						// 07. Send each element of Matrix A
						dos.writeInt(n);
					}

					System.out.println("\nEnter elements row-by-row for Matrix B:");
					for(i=0;i<9;i++)
					{
						n = t.nextInt();

						// 08. Send each element of Matrix B
						dos.writeInt(n);
					}

					System.out.println("\nEnter elements row-by-row for Matrix C:");
					for(i=0;i<9;i++)
					{
						n = t.nextInt();

						// 09. Send each element of Matrix C
						dos.writeInt(n);
					}
				}
			}

			if(c1.equals("2"))
			{
				//10. Receive 10.
				System.out.println(dis.readUTF());
				// Read operation number
				c3 = t.nextInt();
				// 11. Send operation number
				dos.write(c3);

				//12. Receive the result
				System.out.println(dis.readUTF());
			}

			if(c1.equals("0"))
			{
				// EXIT

				// 14. Receive text message
				msgin = dis.readUTF();

				System.out.println(msgin);
				break;
			}
			
		}// while loop

		t.close();
		s.close();
	}
}