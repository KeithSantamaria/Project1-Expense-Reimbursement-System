# Create your Git branch

Now that you have access to the `git repo`, follow the next steps to create your branch

1. Run `git clone https://gitlab.com/revature_batches/0412_javapp_august1.git`
2. Change into the diretory that was created by the command `cd 0412_javapp_august1`
3. Create your branch `git checkout -b <Firstname>_<Lastname>`
4. Create your code directory `mkdir <FirstName>_<Lastname>_Code`
5. Change into that directory `cd <Firstname>_<Lastname>_Code`
6. Add a file name **.gitkeep** `touch .gitkeep`
7. Add the file and directory to for git tracking (in terminal on your branch) `git add <Firstname>_<Lastname>_Code/`
8. Commit the changes to git `git commit -m "<your commit message>"`
9. Push your changes `git push -u origin <FirstName>_<Lastname>`
