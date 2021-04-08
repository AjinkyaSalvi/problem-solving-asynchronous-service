import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Server
{
	public static void main(String args[]) throws Exception
	{
		// Create server socket and accept client's connection
		ServerSocket ss = new ServerSocket(1422);
		Socket s = ss.accept();

		int ai, bi, i, n, ar[], j, a[][] = new int[3][3], b[][] = new int[3][3], c[][] = new int[3][3], nn[][] = new int[3][3], mm[][] = new int[3][3], k, c3;
		String c1, c2, as, bs;
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> operations = new ArrayList<String>();

		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());

		// 01. Send text message
		dos.writeUTF("\nConnection Established");
		dos.flush();

		while(true)
		{
			// 02. Receive the first choice 
			c1=dis.readUTF();

			if(c1.equals("1"))
			{
				// 03. Receive the second choice
				c2 = dis.readUTF();

				if(c2.equals("1"))
				{
					// calculate_pi()

					// Add to results
					results.add("\nThe value of Pi is (22/7) which is 3.14159265359");
					operations.add("Pi");
				}

				if(c2.equals("2"))
				{
					// add(i, j)

					// 04. Receive two numbers
					as = dis.readUTF();
					bs = dis.readUTF();

					ai = Integer.parseInt(as);
					bi = Integer.parseInt(bs);

					ai = ai + bi;

					// Add to results
					results.add(""+ai);
					operations.add("Addition");
				}

				if(c2.equals("3"))
				{
					// sort(arrayA)

					// 05. Receive the number of elements in array
					as = dis.readUTF();

					n = Integer.parseInt(as);
					ar = new int[n];

					for(i=0;i<n;i++)
					{
						// 06. Receive each element
						as = dis.readUTF();

						ar[i] = Integer.parseInt(as);
					}

					// Bubble sort
					for (i=0; i < n-1; i++)
						for (j=0; j < n-i-1; j++)
							if(ar[j] > ar[j+1])
							{
								ai = ar[j];
								ar[j] = ar[j+1];
								ar[j+1] = ai;
							}

					// Convert to String
					as="";
					for(i=0;i<n;i++)
						as = as.concat(ar[i]+"\t");

					// Add to results
					results.add(as);
					operations.add("Sort array");
				}

				if(c2.equals("4"))
				{
					// matrix_multiply(matrixA, matrixB, matrixC)

					// 07. Accept elements of matrix A
					for(i=0;i<3;i++)
						for(j=0;j<3;j++)
							a[i][j] = dis.readInt();

					// 08. Accept elements of matrix B
					for(i=0;i<3;i++)
						for(j=0;j<3;j++)
							b[i][j] = dis.readInt();

					// 09. Accept elements of matrix C
					for(i=0;i<3;i++)
						for(j=0;j<3;j++)
							c[i][j] = dis.readInt();

					for(i=0;i<3;i++)
						for(j=0;j<3;j++)
						{
							nn[i][j]=0;
							for(k=0;k<3;k++)
								nn[i][j] = (a[i][k] * b[k][j]) + nn[i][j];
						}

					as="";
					for(i=0;i<3;i++)
						for(j=0;j<3;j++)
						{
							mm[i][j]=0;
							for(k=0;k<3;k++)
								mm[i][j] = (nn[i][k] * c[k][j]) + mm[i][j];

							// Convert to String
							as = as.concat(mm[i][j]+"\t");
						}

					// Add to results
					results.add(as);
					operations.add("Three Matrices Multiplication");
				}
			}
			
			if(c1.equals("2"))
			{
				// Create operations view
				j=1;
				as="\n\nOperation Number\tOperation\n";
				for(i=0; i<operations.size(); i++)
				{
					as = as.concat(j+"\t\t\t"+operations.get(i)+"\n");
					j++;
				}
				// 10. Ask for operation number
				dos.writeUTF(as.concat("\nEnter the operation number to the corresponding operations above, to get the result:"));

				// 11. Receive the operation number
				c3 = dis.read();

				// 12. Send the result
				dos.writeUTF("\nResult for operation "+c3+": "+results.get(c3-1));
			}

			if(c1.equals("0"))
			{
				// EXIT

				// 13. Send text message
				dos.writeUTF("\nConnection closed");
				dos.flush();

				break;
			}
		}
	}
}
